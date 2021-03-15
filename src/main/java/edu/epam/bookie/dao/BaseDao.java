package edu.epam.bookie.dao;

import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.Entity;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends Entity> {
    Optional<List<T>> findAll() throws DaoException;

    Optional<T> findById(long id) throws DaoException;

    boolean deleteById(long id) throws DaoException;

    Optional<T> create(T entity) throws DaoException;
}
