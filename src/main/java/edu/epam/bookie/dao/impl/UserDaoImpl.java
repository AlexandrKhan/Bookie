package edu.epam.bookie.dao.impl;

import edu.epam.bookie.connection.ConnectionFactory;
import edu.epam.bookie.dao.UserDao;
import edu.epam.bookie.exception.UserDaoException;
import edu.epam.bookie.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    public final static UserDaoImpl INSTANCE = new UserDaoImpl();

    private UserDaoImpl() {
    }

    private static final String ADD_USER = "INSERT INTO user (username, first_name, last_name, email, password, date_of_birth, role) VALUES (?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_USERS = "SELECT id, username, password FROM user";
    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM user WHERE username=?";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM user WHERE id=?";
    private static final String UPDATE_USER_BY_ID = "UPDATE user SET username=?, password=? WHERE id=?";
    private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM user WHERE username=? AND password=?";

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) throws UserDaoException {
        Optional<User> user = Optional.empty();

        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User userTemp = new User();
                userTemp.setUsername(resultSet.getString("username"));
                userTemp.setPassword(resultSet.getString("password"));
                user = Optional.of(userTemp);
            }
        } catch (SQLException e) {
            logger.error("Can't find user with username and pass");
        }
        return user;
    }

    @Override
    public Optional<User> findUserByUsername(String username) throws UserDaoException {
        Optional<User> user = Optional.empty();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
            ResultSet resultSet = statement.executeQuery(SELECT_USER_BY_USERNAME);
            statement.setString(1, username);

            if (resultSet.next()) {
                User userTemp = new User();
                userTemp.setUsername(resultSet.getString("id"));
                userTemp.setUsername(resultSet.getString("username"));
                userTemp.setPassword(resultSet.getString("password"));
                user = Optional.of(userTemp);
            }
        } catch (SQLException e) {
            logger.error("Can't find user with username");
        }
        return user;
    }

    @Override
    public boolean create(User user) throws UserDaoException {
        boolean result = false;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_USER)){
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getFirst_name());
            statement.setString(3, user.getLast_name());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setDate(6, Date.valueOf(user.getDate_of_birth()));
            statement.setString(7, user.getRole().toString());
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Can't add user");
        }
        return result;
    }

    @Override
    public Optional<List<User>> findAll() throws UserDaoException {
        Optional<List<User>> users = Optional.empty();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                userList.add(user);
            }
            users = Optional.of(userList);
        } catch (SQLException e) {
            logger.error("Can't find all users");
        }
        return users;
    }



    @Override
    public Optional<User> findById(long id) throws UserDaoException {
        Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            ResultSet resultSet = statement.executeQuery(SELECT_USER_BY_ID);
            User userTemp = new User();
            while (resultSet.next()) {
                userTemp.setUsername(resultSet.getString("id"));
                userTemp.setUsername(resultSet.getString("username"));
                userTemp.setPassword(resultSet.getString("password"));
            }
            user = Optional.of(userTemp);

        } catch (SQLException e) {
            logger.error("Can't find user with username");
        }
        return user;
    }

    @Override
    public boolean deleteById(long id) throws UserDaoException {
        boolean result = false;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID)) {
            statement.setLong(1, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Can't delete user by id");
        }
        return result;
    }

    @Override
    public boolean update(long id, String... params) throws UserDaoException {
        boolean result = false;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_BY_ID)) {
            statement.setString(1, params[0]);
            statement.setString(2, params[1]);
            statement.setLong(3, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Can't update user at id: " + id);
        }
        return result;
    }
}
