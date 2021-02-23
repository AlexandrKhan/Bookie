package edu.epam.bookie.dao.impl;

import edu.epam.bookie.connection.ConnectionPool;
import edu.epam.bookie.dao.BetDao;
import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.sport.Bet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class BetDaoImpl implements BetDao {
    private static final Logger logger = LogManager.getLogger(BetDaoImpl.class);
    public static final BetDaoImpl betDao = new BetDaoImpl();
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String PLACE_BET = "INSERT INTO bookie.bet SET user_id=?, match_id=?, bet_date=?, bet_time=?, bet_amount=?, bet_on_result=?";

    private BetDaoImpl() {
    }

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

    @Override
    public boolean placeBet(Bet bet) throws DaoException {
        boolean result = false;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(PLACE_BET)) {
            statement.setInt(1, bet.getUserId());
            statement.setInt(2, bet.getMatchId());
            statement.setDate(3, Date.valueOf(bet.getBetDate()));
            statement.setTime(4, Time.valueOf(bet.getBetTime()));
            statement.setBigDecimal(5, bet.getBetAmount());
            statement.setString(6, bet.getBetOnResult().name());
             result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error placing bet", e);
        }
        return result;
    }
}
