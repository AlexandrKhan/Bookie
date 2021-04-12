package edu.epam.bookie.dao.impl;

import edu.epam.bookie.connection.ConnectionPool;
import edu.epam.bookie.dao.MatchDao;
import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.Comment;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatchDaoImpl implements MatchDao {
    private static final Logger logger = LogManager.getLogger(MatchDaoImpl.class);
    public final static MatchDaoImpl matchDao = new MatchDaoImpl();

    private static final String SELECT_ALL_MATCHES = "SELECT * FROM bookie.match LEFT JOIN bookie.match_result ON bookie.match.id = bookie.match_result.id";
    private static final String ADD_MATCH = "INSERT INTO bookie.match(home_team, away_team, start_date, start_time, home_coeff, draw_coeff, away_coeff) VALUES (?,?,?,?,?,?,?)";
    private static final String ADD_DEFAULT_MATCH_PROGRESS = "INSERT INTO bookie.match_result(id, home_team_goals, away_team_goals, result, match_progress) VALUES (LAST_INSERT_ID(), 0, 0, 'DRAW', 'NOT_STARTED')";
    private static final String DELETE_MATCH_BY_ID = "DELETE FROM bookie.match WHERE id=?";
    private static final String SET_GOALS_RESULT_AND_OVER_MATCH_BY_ID = "UPDATE bookie.match_result SET home_team_goals=?, away_team_goals=?, result=?, match_progress='OVER' WHERE id=?";
    private static final String UPDATE_DATE_TIME_AT_MATCH = "UPDATE bookie.match SET start_date=?, start_time=? WHERE id=?";
    private static final String FIND_MATCHES_BY_TEAM = "SELECT * FROM bookie.match LEFT JOIN bookie.match_result ON bookie.match.id = bookie.match_result.id WHERE bookie.match.home_team LIKE ? OR bookie.match.away_team LIKE ?";
    private static final String FIND_MATCHES_BY_USER_ID = "SELECT DISTINCT M.id, home_team, away_team, start_date, start_time, home_coeff, draw_coeff, away_coeff, home_team_goals, away_team_goals, result, match_progress FROM bookie.match AS M JOIN bookie.match_result AS MR ON M.id = MR.id JOIN bookie.bet AS BB ON M.id = BB.match_id WHERE BB.user_id=?";
    private static final String SELECT_MATCH_BY_ID = "SELECT * FROM bookie.match JOIN bookie.match_result ON bookie.match.id = bookie.match_result.id WHERE bookie.match.id=?";
    private static final String FIND_COMMENTS = "SELECT * FROM bookie.match_comments WHERE match_id=?";

    private MatchDaoImpl() {
    }

    @Override
    public Optional<Match> create(Match match) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_MATCH);
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_DEFAULT_MATCH_PROGRESS)) {
            statement.setString(1, match.getHomeTeam().name());
            statement.setString(2, match.getAwayTeam().name());
            statement.setDate(3, Date.valueOf(match.getStartDate()));
            statement.setTime(4, Time.valueOf(match.getStartTime()));
            statement.setBigDecimal(5, match.getHomeCoeff());
            statement.setBigDecimal(6, match.getDrawCoeff());
            statement.setBigDecimal(7, match.getAwayCoeff());

            statement.execute();
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Cant create match", e);
            throw new DaoException(e);
        }
        return Optional.of(match);
    }

    @Override
    public boolean setGoalsResultAndOverMatchById(Long id, int home, int away, Result matchResult) throws DaoException {
        boolean result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_GOALS_RESULT_AND_OVER_MATCH_BY_ID)) {
            statement.setLong(1, home);
            statement.setLong(2, away);
            statement.setString(3, matchResult.name());
            statement.setLong(4, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Cant set goals by id", e);
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean updateDateTimeAtNotStartedMatch(Long matchId, LocalDate date, LocalTime time) throws DaoException {
        boolean result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_DATE_TIME_AT_MATCH)) {
            statement.setDate(1, Date.valueOf(date));
            statement.setTime(2, Time.valueOf(time));
            statement.setLong(3, matchId);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Can't update date time at match with id: {}", matchId, e);
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Optional<List<Match>> findAll() throws DaoException {
        Optional<List<Match>> matches;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MATCHES)) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_MATCHES);
            List<Match> matchList = new ArrayList<>();
            while (resultSet.next()) {
                Match match = new Match();
                setNotStartedMatchFields(resultSet, match);
                setOverMatchFields(resultSet, match);
                matchList.add(match);
            }
            matches = Optional.of(matchList);
        } catch (SQLException e) {
            logger.error("Can't find all matches", e);
            throw new DaoException(e);
        }
        return matches;
    }

    @Override
    public Optional<Match> findById(long id) throws DaoException {
        Optional<Match> match;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_MATCH_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Match matchTemp = new Match();
            while (resultSet.next()) {
                setNotStartedMatchFields(resultSet, matchTemp);
                setOverMatchFields(resultSet, matchTemp);
            }
            match = Optional.of(matchTemp);
        } catch (SQLException e) {
            logger.error("Cant select match by id: {}", id, e);
            throw new DaoException(e);
        }
        return match;
    }


    @Override
    public Optional<List<Match>> findMatchesByTeam(String team) throws DaoException {
        Optional<List<Match>> matches;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_MATCHES_BY_TEAM)) {
            statement.setString(1, "%" + team + "%");
            statement.setString(2, "%" + team + "%");
            ResultSet resultSet = statement.executeQuery();
            List<Match> matchList = new ArrayList<>();
            while (resultSet.next()) {
                Match match = new Match();
                setNotStartedMatchFields(resultSet, match);
                setOverMatchFields(resultSet, match);
                matchList.add(match);
            }
            matches = Optional.of(matchList);
        } catch (SQLException e) {
            logger.error("Cant gind matches by team", e);
            throw new DaoException(e);
        }
        return matches;
    }

    @Override
    public Optional<List<Match>> findMatchesOnWhichUserBetByUserId(Long id) throws DaoException {
        Optional<List<Match>> matches;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_MATCHES_BY_USER_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Match> matchList = new ArrayList<>();
            while (resultSet.next()) {
                Match match = new Match();
                setNotStartedMatchFields(resultSet, match);
                setOverMatchFields(resultSet, match);
                matchList.add(match);
            }
            matches = Optional.of(matchList);
        } catch (SQLException e) {
            logger.error("Cant find matches by user who bet", e);
            throw new DaoException(e);
        }
        return matches;
    }

    @Override
    public Optional<List<Comment>> findCommentsForMatch(Long id) throws DaoException {
        Optional<List<Comment>> comments;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_COMMENTS)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Comment> commentList = new ArrayList<>();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt(DatabaseColumn.ID));
                comment.setMatchId(resultSet.getInt(DatabaseColumn.MATCH_ID));
                comment.setUserId(resultSet.getInt(DatabaseColumn.USER_ID));
                comment.setComment(resultSet.getString(DatabaseColumn.COMMENT));
                comment.setDate(resultSet.getDate(DatabaseColumn.DATE).toLocalDate());
                comment.setTime(resultSet.getTime(DatabaseColumn.TIME).toLocalTime());
                commentList.add(comment);
            }
            comments = Optional.of(commentList);
        } catch (SQLException e) {
            logger.error("Cant find matches by user who bet", e);
            throw new DaoException(e);
        }
        return comments;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        boolean result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_MATCH_BY_ID)) {
            statement.setLong(1, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Can't delete match by id", e);
            throw new DaoException(e);
        }
        return result;
    }

    private void setNotStartedMatchFields(ResultSet resultSet, Match match) throws SQLException {
        match.setId(resultSet.getInt(DatabaseColumn.ID));
        match.setFirstTeam(resultSet.getString(DatabaseColumn.HOME_TEAM));
        match.setSecondTeam(resultSet.getString(DatabaseColumn.AWAY_TEAM));
        match.setStartDate((resultSet.getDate(DatabaseColumn.MATCH_START_DATE).toLocalDate()));
        match.setStartTime((resultSet.getTime(DatabaseColumn.MATCH_START_TIME).toLocalTime()));
        match.setHomeCoeff(resultSet.getBigDecimal(DatabaseColumn.HOME_COEFF));
        match.setDrawCoeff(resultSet.getBigDecimal(DatabaseColumn.DRAW_COEFF));
        match.setAwayCoeff(resultSet.getBigDecimal(DatabaseColumn.AWAY_COEFF));
    }

    private void setOverMatchFields(ResultSet resultSet, Match match) throws SQLException {
        match.setHomeTeamGoals(resultSet.getInt(DatabaseColumn.HOME_TEAM_GOALS));
        match.setAwayTeamGoals(resultSet.getInt(DatabaseColumn.AWAY_TEAM_GOALS));
        match.setResult(resultSet.getString(DatabaseColumn.MATCH_RESULT));
        match.setMatchProgress(resultSet.getString(DatabaseColumn.MATCH_PROGRESS));
    }
}
