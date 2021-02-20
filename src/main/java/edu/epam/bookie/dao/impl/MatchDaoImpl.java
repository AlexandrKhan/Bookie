package edu.epam.bookie.dao.impl;

import edu.epam.bookie.connection.ConnectionFactory;
import edu.epam.bookie.dao.MatchDao;
import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatchDaoImpl implements MatchDao {
    private static final Logger logger = LogManager.getLogger(MatchDaoImpl.class);

    private MatchDaoImpl() {
    }

    public final static MatchDaoImpl INSTANCE = new MatchDaoImpl();

    private static final String SELECT_ALL_MATCHES = "SELECT * FROM bookie.match";
    private static final String SELECT_ALL_NOT_STARTED_MATCHES = "SELECT * FROM bookie.match WHERE match_progress='NOT_STARTED'";
    private static final String ADD_MATCH = "INSERT INTO bookie.match(first, second, start_date, start_time)" +
            "VALUES (?,?,?,?)";
    private static final String DELETE_MATCH_BY_ID = "DELETE FROM bookie.match WHERE id=?";
    private static final String SET_GOALS_AND_RESULT_BY_ID = "UPDATE bookie.match SET first_goal=?, second_goal=?, result=? WHERE id=?";
    private static final String SET_MATCH_PROGRESS_OVER_BY_ID = "UPDATE bookie.match SET match_progress='OVER' WHERE id=?";
    private static final String SELECT_MATCH_BY_ID = "SELECT * FROM bookie.match WHERE id=?";

    @Override
    public Match create(Match match) throws DaoException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_MATCH)) {
            statement.setString(1, match.getFirstTeam().name());
            statement.setString(2, match.getSecondTeam().name());
            statement.setDate(3, Date.valueOf(match.getStartDate()));
            statement.setTime(4, Time.valueOf(match.getStartTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return match;
    }

    @Override
    public Optional<List<Match>> findAll() throws DaoException {
        Optional<List<Match>> matches = Optional.empty();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MATCHES)) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_MATCHES);
            List<Match> matchList = new ArrayList<>();
            while (resultSet.next()) {
                Match match = new Match();
                match.setId(resultSet.getInt("id"));
                match.setFirstTeam(resultSet.getString("first"));
                match.setSecondTeam(resultSet.getString("second"));
                match.setStartDate((resultSet.getDate("start_date").toLocalDate()));
                match.setStartTime((resultSet.getTime("start_time").toLocalTime()));
                match.setFirstTeamGoals(resultSet.getInt("first_goal"));
                match.setFirstTeamGoals(resultSet.getInt("second_goal"));
                if (resultSet.getString("result") != null) {
                    match.setResult(resultSet.getString("result"));
                }
                match.setMatchProgress(resultSet.getString("match_progress"));
                matchList.add(match);
            }
            matches = Optional.of(matchList);
        } catch (SQLException e) {
            logger.error("Can't find all matches: " + e);
        }
        return matches;
    }

    @Override
    public Optional<Match> findById(long id) throws DaoException {
        Optional<Match> match = Optional.empty();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_MATCH_BY_ID)) {
            ResultSet resultSet = statement.executeQuery(SELECT_MATCH_BY_ID);
            while (resultSet.next()) {
                Match matchTemp = new Match();
                matchTemp.setId(resultSet.getInt("id"));
                matchTemp.setFirstTeam(resultSet.getString("first"));
                matchTemp.setSecondTeam(resultSet.getString("second"));
                matchTemp.setStartDate((resultSet.getDate("start_date").toLocalDate()));
                matchTemp.setStartTime((resultSet.getTime("start_time").toLocalTime()));
                matchTemp.setFirstTeamGoals(resultSet.getInt("first_goal"));
                matchTemp.setFirstTeamGoals(resultSet.getInt("second_goal"));
                if (resultSet.getString("result") != null) {
                    matchTemp.setResult(resultSet.getString("result"));
                }
                matchTemp.setMatchProgress(resultSet.getString("match_progress"));
            }
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cant select match by id: {}", id);
        }
        return match;
    }

    @Override
    public Optional<List<Match>> findAllNotStartedMatches() throws DaoException {
        Optional<List<Match>> matches = Optional.empty();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_NOT_STARTED_MATCHES)) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_NOT_STARTED_MATCHES);
            List<Match> matchList = new ArrayList<>();
            while (resultSet.next()) {
                Match match = new Match();
                match.setId(resultSet.getInt("id"));
                match.setFirstTeam(resultSet.getString("first"));
                match.setSecondTeam(resultSet.getString("second"));
                match.setStartDate((resultSet.getDate("start_date").toLocalDate()));
                match.setStartTime((resultSet.getTime("start_time").toLocalTime()));
                matchList.add(match);
            }
            matches = Optional.of(matchList);
        } catch (SQLException e) {
            logger.error("Cant find all not started matches");
        }
        return matches;
    }

    @Override
    public boolean setGoalsAndResultById(Long id, int first, int second, Result matchResult) throws DaoException {
        boolean result = false;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_GOALS_AND_RESULT_BY_ID)) {
            statement.setLong(1, first);
            statement.setLong(2, second);
            statement.setString(3, matchResult.name());
            statement.setLong(4, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Cant set goals by id");
        }
        return result;
    }

    @Override
    public boolean setMatchProgressOverById(Long id) throws DaoException {
        boolean result = false;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_MATCH_PROGRESS_OVER_BY_ID)) {
            statement.setLong(1, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Cant over match");
        }
        return result;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        boolean result;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_MATCH_BY_ID)) {
            statement.setLong(1, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Can't delete match by id");
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean update(long id, String... params) throws DaoException {
        return false;
    }


}
