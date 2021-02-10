package edu.epam.bookie.dao.impl;

import edu.epam.bookie.connection.ConnectionFactory;
import edu.epam.bookie.dao.MatchDao;
import edu.epam.bookie.exception.UserDaoException;
import edu.epam.bookie.model.Match;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatchDaoImpl implements MatchDao {
    private static final Logger logger = LogManager.getLogger(MatchDaoImpl.class);

    private MatchDaoImpl() {
    }

    public final static MatchDaoImpl INSTANCE = new MatchDaoImpl();

    private static final String SELECT_ALL_MATCHES = "SELECT * FROM match";

    @Override
    public Optional<List<Match>> findAll() throws UserDaoException {
        Optional<List<Match>> matches = Optional.empty();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MATCHES)) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_MATCHES);
            List<Match> matchList = new ArrayList<>();
            while (resultSet.next()) {
                Match match = new Match();
               /* match.setFirst_team(resultSet.getString("first_team"));
                match.setSecond_team(resultSet.getString("second_team"));*/
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
        return null;
    }

    @Override
    public boolean deleteById(long id) throws UserDaoException {
        return false;
    }

    @Override
    public boolean create(Match entity) throws UserDaoException {
        return false;
    }

    @Override
    public boolean update(long id, String... params) throws UserDaoException {
        return false;
    }
}
