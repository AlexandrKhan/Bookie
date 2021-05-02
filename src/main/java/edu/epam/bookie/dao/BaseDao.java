package edu.epam.bookie.dao;

import edu.epam.bookie.dao.impl.MatchDaoImpl;
import edu.epam.bookie.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Base Dao Interface
 * @param <T> type param
 */
public interface BaseDao<T> {
    Logger logger = LogManager.getLogger(MatchDaoImpl.class);

    Optional<List<T>> findAll() throws DaoException;

    Optional<T> findById(int id) throws DaoException;

    Optional<T> create(T entity) throws DaoException;

    default void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.error("Rollback exception", e);
            }
        }
    }
}
