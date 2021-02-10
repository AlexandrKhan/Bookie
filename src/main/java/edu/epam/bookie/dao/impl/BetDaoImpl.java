package edu.epam.bookie.dao.impl;

import edu.epam.bookie.dao.BetDao;
import edu.epam.bookie.exception.UserDaoException;
import edu.epam.bookie.model.Bet;

import java.util.List;
import java.util.Optional;

public class BetDaoImpl implements BetDao {
    @Override
    public Optional<List<Bet>> findAll() throws UserDaoException {
        return null;
    }

    @Override
    public Optional<Bet> findById(long id) throws UserDaoException {
        return null;
    }

    @Override
    public boolean deleteById(long id) throws UserDaoException {
        return false;
    }

    @Override
    public boolean create(Bet entity) throws UserDaoException {
        return false;
    }

    @Override
    public boolean update(long id, String... params) throws UserDaoException {
        return false;
    }
}
