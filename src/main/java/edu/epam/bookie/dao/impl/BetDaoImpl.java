package edu.epam.bookie.dao.impl;

import edu.epam.bookie.dao.BetDao;
import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.sport.Bet;

import java.util.List;
import java.util.Optional;

public class BetDaoImpl implements BetDao {
    @Override
    public Optional<List<Bet>> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Bet> findById(long id) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        return false;
    }

    @Override
    public Bet create(Bet entity) throws DaoException {
        return entity;
    }

    @Override
    public boolean update(long id, String... params) throws DaoException {
        return false;
    }
}
