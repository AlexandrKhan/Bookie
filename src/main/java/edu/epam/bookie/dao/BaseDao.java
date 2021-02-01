package edu.epam.bookie.dao;

import edu.epam.bookie.exception.UserDaoException;
import edu.epam.bookie.model.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends Entity> {
    static final Logger logger = LogManager.getLogger(BaseDao.class);

    Optional<List<T>> findAll() throws UserDaoException;

    Optional<T> findById(long id) throws UserDaoException;

    boolean deleteById(long id) throws UserDaoException;

    boolean deleteByName(String name) throws UserDaoException;

    boolean create(T entity) throws UserDaoException;

    boolean update(long id, String...params) throws UserDaoException;
}
