package edu.epam.bookie.connection;

import edu.epam.bookie.exception.PropertyReaderException;
import edu.epam.bookie.util.PropertiesPath;
import edu.epam.bookie.util.PropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    private static final int DEFAULT_POOL_SIZE = 32;
    private final BlockingQueue<ProxyConnection> freeConnection;
    private final BlockingQueue<ProxyConnection> releasedConnection;
    private static final String DRIVER = "db.driver";
    private static final String URL = "db.url";

    private ConnectionPool() {
        String propertiesPath = PropertiesPath.DB_PROPERTIES;
        Properties properties;
        try {
            properties = PropertiesReader.readProperties(propertiesPath);
        } catch (PropertyReaderException e) {
            throw new RuntimeException("Can't read DB properties", e);
        }

        String driver = properties.getProperty(DRIVER);
        String url = properties.getProperty(URL);

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't register driver", e);
        }

        freeConnection = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        releasedConnection = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, properties);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnection.add(proxyConnection);
            } catch (SQLException e) {
                logger.error("Can'r create connection", e);
            }
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnection.take();
            releasedConnection.add(connection);
        } catch (InterruptedException e) {
            logger.error("Can't provide connection", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            if (releasedConnection.remove(connection)) {
                freeConnection.offer((ProxyConnection) connection);
            }
        } else {
            logger.error("Released connection isn't proxy");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnection.take().finalClose();
            } catch (SQLException | InterruptedException e) {
                logger.error("Can't close connection", e);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Error while deregister drivers", e);
            }
        }
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }
}
