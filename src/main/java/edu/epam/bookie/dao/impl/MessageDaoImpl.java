package edu.epam.bookie.dao.impl;

import edu.epam.bookie.connection.ConnectionPool;
import edu.epam.bookie.dao.MessageDao;
import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageDaoImpl implements MessageDao {
    private static final Logger logger = LogManager.getLogger(MessageDaoImpl.class);
    public final static MessageDaoImpl messageDao = new MessageDaoImpl();
    private final ConnectionPool pool = ConnectionPool.getInstance();


    private static final String SELECT_ALL_MESSAGES = "SELECT * FROM bookie.message LEFT JOIN bookie.match_result ON bookie.match.id = bookie.match_result.id";
    private static final String SELECT_ALL_MESSAGES_OF_USER = "SELECT * FROM bookie.message WHERE user_id=?";
    private static final String ADD_MATCH = "INSERT INTO bookie.match(home_team, away_team, start_date, start_time, home_coeff, draw_coeff, away_coeff) VALUES (?,?,?,?,?,?,?)";

    private MessageDaoImpl() {
    }

    @Override
    public Optional<List<Message>> findAll() throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Message> findById(long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Message> create(Message entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<List<Message>> findAllMessagesOfUser(int id) throws DaoException {
        Optional<List<Message>> messages;
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MESSAGES_OF_USER)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Message> tempList = new ArrayList<>();
            while (resultSet.next()) {
                Message message = new Message();
                message.setId(resultSet.getInt(DatabaseColumn.ID));
                message.setUserId(resultSet.getInt(DatabaseColumn.USER_ID));
                message.setDate(resultSet.getDate(DatabaseColumn.DATE).toLocalDate());
                message.setTime(resultSet.getTime(DatabaseColumn.TIME).toLocalTime());
                message.setMessage(resultSet.getString(DatabaseColumn.MESSAGE));
                tempList.add(message);
            }
            messages = Optional.of(tempList);
        } catch (SQLException e) {
            logger.error("Cant find messages of user", e);
            throw new DaoException(e);
        }
        return messages;
    }
}
