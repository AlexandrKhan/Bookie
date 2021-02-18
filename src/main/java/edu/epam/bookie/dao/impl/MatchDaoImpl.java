package edu.epam.bookie.dao.impl;

import edu.epam.bookie.connection.ConnectionFactory;
import edu.epam.bookie.dao.MatchDao;
import edu.epam.bookie.exception.UserDaoException;
import edu.epam.bookie.model.sport.Match;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatchDaoImpl implements MatchDao {
    private static final Logger logger = LogManager.getLogger(MatchDaoImpl.class);

    private MatchDaoImpl() {
    }

    public final static MatchDaoImpl INSTANCE = new MatchDaoImpl();

    private static final String SELECT_ALL_MATCHES = "SELECT * FROM bookie.match";
    private static final String ADD_MATCH = "INSERT INTO bookie.match(first, second, start_date, start_time)" +
            "VALUES (?,?,?,?)";

    @Override
    public Match create(Match match) throws UserDaoException {
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
    public Optional<List<Match>> findAll() throws UserDaoException {
        Optional<List<Match>> matches = Optional.empty();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MATCHES)) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_MATCHES);
            List<Match> matchList = new ArrayList<>();
            while (resultSet.next()) {
                Match match = new Match();
                match.setId(resultSet.getInt("id"));
                match.setFirstTeam(resultSet.getString("first_team"));
                match.setSecondTeam(resultSet.getString("second_team"));
                match.setStartDate((resultSet.getDate("second_team").toLocalDate()));
                match.setStartTime((resultSet.getTime("second_team").toLocalTime()));
                match.setResult(resultSet.getString("second_team"));
                match.setMatchProgress(resultSet.getString("second_team"));
                matchList.add(match);
            }
            matches = Optional.of(matchList);
        } catch (SQLException e) {
            logger.error("Can't find all matches");
        }
        return matches;
    }

    @Override
    public Optional<Match> findById(long id) throws UserDaoException {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(long id) throws UserDaoException {
        return false;
    }

    @Override
    public boolean update(long id, String... params) throws UserDaoException {
        return false;
    }
}
