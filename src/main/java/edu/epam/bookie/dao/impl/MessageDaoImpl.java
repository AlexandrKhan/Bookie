package edu.epam.bookie.dao.impl;

import edu.epam.bookie.connection.ConnectionPool;
import edu.epam.bookie.dao.MessageDao;
import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageDaoImpl implements MessageDao {
    private static final Logger logger = LogManager.getLogger(MessageDaoImpl.class);
    public final static MessageDaoImpl messageDao = new MessageDaoImpl();
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String SELECT_ALL_MESSAGES_OF_USER = "SELECT * FROM bookie.message WHERE user_id=?";
    private static final String ADD_MESSAGE =
            "INSERT INTO bookie.message(user_id, date, time, text, theme) VALUES (?,?,?,?,?)";

    private MessageDaoImpl() {
    }

    @Override
    public Optional<List<Message>> findAll() throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Message> findById(int id) throws DaoException {
        throw new UnsupportedOperationException();
    }


    @Override
    public Optional<Message> create(Message message) throws DaoException {
        try (Connection connection = pool.getConnection();
        PreparedStatement statement = connection.prepareStatement(ADD_MESSAGE)) {
            statement.setInt(1, message.getUserId());
            statement.setDate(2, Date.valueOf(message.getDate()));
            statement.setTime(3, Time.valueOf(message.getTime()));
            statement.setString(4, message.getMessage());
            statement.setString(5, message.getTheme().name());
            statement.execute();
        } catch (SQLException e) {
            logger.error("Eror creating message");
            throw new DaoException(e);
        }
        return Optional.of(message);
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
                message.setTheme(resultSet.getString(DatabaseColumn.THEME));
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
