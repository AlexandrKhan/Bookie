package edu.epam.bookie.connection;

import edu.epam.bookie.exception.PropertyReaderException;
import edu.epam.bookie.util.PropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static volatile ConnectionPool instance;
    private static final int DEFAULT_POOL_SIZE = 32;
    private final BlockingQueue<ProxyConnection> freeConnection;
    private final Queue<ProxyConnection> releasedConnection;
    private static final String DRIVER = "db.driver";
    private static final String URL = "db.url";
    private static final String DB_PROPERTIES = "property/database.properties";
    private static final AtomicBoolean poolInitialized = new AtomicBoolean(false);
    private static final Lock lock = new ReentrantLock();

    private ConnectionPool() {
        Properties properties;
        try {
            properties = PropertiesReader.readProperties(DB_PROPERTIES);
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
        releasedConnection = new ArrayDeque<>();

        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, properties);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnection.add(proxyConnection);
            } catch (SQLException e) {
                logger.error("Can't create connection", e);
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (!poolInitialized.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    poolInitialized.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
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
        Iterator<Driver> drivers = DriverManager.getDrivers().asIterator();
        while (drivers.hasNext()) {
            Driver driver = drivers.next();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Error while deregister drivers", e);
            }
        }
    }
}
