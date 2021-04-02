package edu.epam.bookie.dao.impl;

import edu.epam.bookie.connection.ConnectionPool;
import edu.epam.bookie.dao.BetDao;
import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.sport.Bet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BetDaoImpl implements BetDao {
    private static final Logger logger = LogManager.getLogger(BetDaoImpl.class);
    public static final BetDaoImpl betDao = new BetDaoImpl();
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String PLACE_BET = "INSERT INTO bookie.bet SET user_id=?, match_id=?, bet_date=?, bet_time=?, bet_amount=?, bet_on_result=?, bet_coeff=?";
    private static final String SELECT_ALL_BETS = "SELECT * FROM bookie.bet";
    private static final String SELECT_BET_BY_ID = "SELECT * FROM bookie.bet WHERE id=?";
    private static final String SELECT_ALL_BETS_ON_MATCH_ID = "SELECT * FROM bookie.bet WHERE match_id=?";
    private static final String PAY_BETS_ON_BET_ID = "UPDATE bookie.user, bookie.bet SET money_balance = money_balance + ?, bet_status='WON' WHERE bookie.bet.id=? AND bookie.user.id=bookie.bet.user_id";
    private static final String SET_BET_STATUS_LOST = "UPDATE bookie.bet SET bet_status='LOST' WHERE id=?";
    private static final String SELECT_ALL_BETS_OF_USER = "SELECT * FROM bookie.bet WHERE user_id=?";
    private static final String SELECT_ALL_BETS_OF_DATE = "SELECT * FROM bookie.bet WHERE bet_date=?";

    private BetDaoImpl() {
    }

    @Override
    public Optional<Bet> create(Bet bet) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(PLACE_BET)) {
            statement.setInt(1, bet.getUserId());
            statement.setInt(2, bet.getMatchId());
            statement.setDate(3, Date.valueOf(bet.getBetDate()));
            statement.setTime(4, Time.valueOf(bet.getBetTime()));
            statement.setBigDecimal(5, bet.getBetAmount());
            statement.setString(6, bet.getBetOnResult().name());
            statement.setBigDecimal(7, bet.getBetCoeff());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error placing bet", e);
            throw new DaoException(e);
        }
        return Optional.of(bet);
    }

    @Override
    public boolean payBets(Bet bet) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(PAY_BETS_ON_BET_ID)) {
            statement.setBigDecimal(1, bet.getBetAmount().multiply(bet.getBetCoeff()));
            statement.setLong(2, bet.getId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error paying for bets");
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean betLost(Bet bet) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_BET_STATUS_LOST)) {
            statement.setLong(1, bet.getId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Cant set bet lost", e);
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Optional<List<Bet>> selectBetsByMatchId(Long matchId) throws DaoException {
        Optional<List<Bet>> bets;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BETS_ON_MATCH_ID)) {
            statement.setLong(1, matchId);
            ResultSet resultSet = statement.executeQuery();
            List<Bet> betsTemp = new ArrayList<>();
            while (resultSet.next()) {
                Bet bet = new Bet();
                setBetFields(resultSet, bet);
                betsTemp.add(bet);
            }
            bets = Optional.of(betsTemp);
        } catch (SQLException e) {
            logger.error("Cant select bets by match id: {}", matchId, e);
            throw new DaoException(e);
        }
        return bets;
    }

    @Override
    public Optional<List<Bet>> selectBetsByUserId(Long userId) throws DaoException {
        Optional<List<Bet>> bets;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BETS_OF_USER)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<Bet> betsTemp = new ArrayList<>();
            while (resultSet.next()) {
                Bet bet = new Bet();
                setBetFields(resultSet, bet);
                betsTemp.add(bet);
            }
            bets = Optional.of(betsTemp);
        } catch (SQLException e) {
            logger.error("Cant select bets by user id: {}", userId, e);
            throw new DaoException(e);
        }
        return bets;
    }

    @Override
    public Optional<List<Bet>> findAll() throws DaoException {
        Optional<List<Bet>> bets;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BETS)) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_BETS);
            List<Bet> betsTemp = new ArrayList<>();
            while (resultSet.next()) {
                Bet bet = new Bet();
                setBetFields(resultSet, bet);
                betsTemp.add(bet);
            }
            bets = Optional.of(betsTemp);

        } catch (SQLException e) {
            logger.error("Erorr finding all bets", e);
            throw new DaoException(e);
        }
        return bets;
    }

    @Override
    public Optional<Bet> findById(long id) throws DaoException {
        Optional<Bet> bet;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery(SELECT_BET_BY_ID);
            Bet betTemp = new Bet();
            while (resultSet.next()) {
                setBetFields(resultSet, betTemp);
            }
            bet = Optional.of(betTemp);
        } catch (SQLException e) {
            logger.error("Can't find bet with id: {}", id, e);
            throw new DaoException(e);
        }
        return bet;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    private void setBetFields(ResultSet resultSet, Bet bet) throws SQLException {
        bet.setId(resultSet.getInt(DatabaseColumn.ID));
        bet.setUserId(resultSet.getInt(DatabaseColumn.USER_ID));
        bet.setMatchId(resultSet.getInt(DatabaseColumn.MATCH_ID));
        bet.setBetDate(resultSet.getDate(DatabaseColumn.BET_DATE).toLocalDate());
        bet.setBetTime(resultSet.getTime(DatabaseColumn.BET_TIME).toLocalTime());
        bet.setBetAmount(resultSet.getBigDecimal(DatabaseColumn.BET_AMOUNT));
        bet.setBetOnResult(resultSet.getString(DatabaseColumn.BET_ON_RESULT));
        bet.setBetStatus(resultSet.getString(DatabaseColumn.BET_STATUS));
        bet.setBetCoeff(resultSet.getBigDecimal(DatabaseColumn.BET_COEFF));
    }

}
