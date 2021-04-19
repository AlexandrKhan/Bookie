package edu.epam.bookie.dao.impl;

import edu.epam.bookie.connection.ConnectionPool;
import edu.epam.bookie.dao.UserDao;
import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    public static final  UserDaoImpl userDao = new UserDaoImpl();
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String ADD_USER = "INSERT INTO bookie.user (username, first_name, last_name, email, password, date_of_birth, role, token) VALUES (?,?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_USERS = "SELECT * FROM bookie.user";
    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM bookie.user WHERE username=?";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM bookie.user WHERE id=?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM bookie.user WHERE id=?";
    private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM bookie.user WHERE username=? AND password=?";
    private static final String SELECT_EMAIL_BY_ID = "SELECT email FROM bookie.user WHERE id=?";
    private static final String ACTIVATE_ACCOUNT = "UPDATE bookie.user SET status='ACTIVATED' WHERE token=?";
    private static final String VERIFY_ACCOUNT = "UPDATE bookie.user SET status='VERIFIED' WHERE id=?";
    private static final String EMAIL_EXISTS = "SELECT * FROM bookie.user WHERE email=?";
    private static final String USERNAME_EXISTS = "SELECT * FROM bookie.user WHERE username=?";
    private static final String BLOCK_USER = "UPDATE bookie.user SET status='BLOCKED' WHERE id=?";
    private static final String UNBLOCK_USER = "UPDATE bookie.user SET status='VERIFIED' WHERE id=?";
    private static final String CASH_IN = "UPDATE bookie.user SET money=money + ? WHERE id=?";
    private static final String WITHDRAW_MONEY = "UPDATE bookie.user SET money=money - ? WHERE id=?";
    private static final String UPLOAD_SCAN = "UPDATE bookie.user SET passport_scan=? WHERE id=?";

    private UserDaoImpl() {
    }

    @Override
    public Optional<User> create(User user) throws DaoException {
        try (Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(ADD_USER)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setDate(6, Date.valueOf(user.getDateOfBirth()));
            statement.setString(7, user.getRole().name());
            statement.setString(8, user.getToken());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't add user");
            throw new DaoException(e);
        }
        return Optional.of(user);
    }

    @Override
    public Optional<List<User>> findAll() throws DaoException {
        Optional<List<User>> users;

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                setUserFields(resultSet, user);
                userList.add(user);
            }
            users = Optional.of(userList);
        } catch (SQLException e) {
            logger.error("Can't find all users");
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public Optional<User> findUserByUsername(String username) throws DaoException {
        Optional<User> user = Optional.empty();

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery(SELECT_USER_BY_USERNAME);

            if (resultSet.next()) {
                User userTemp = new User();
                setUserFields(resultSet, userTemp);
                user = Optional.of(userTemp);
            }
        } catch (SQLException e) {
            logger.error("Can't find user with username");
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) throws DaoException {
        Optional<User> user = Optional.empty();

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User userTemp = new User();
                setUserFields(resultSet, userTemp);
                userTemp.setPassword(resultSet.getString(DatabaseColumn.PASSWORD));
                user = Optional.of(userTemp);
            }
        } catch (SQLException e) {
            logger.error("Can't find user with username and pass");
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public Optional<String> findEmailById(int id) throws DaoException {
        Optional<String> email = Optional.empty();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_EMAIL_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                email = Optional.of(resultSet.getString(DatabaseColumn.EMAIL));
            }
        } catch (SQLException e) {
            logger.error("Can't find user with such id");
            throw new DaoException(e);
        }
        return email;
    }

    @Override
    public boolean activateAccount(String token) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ACTIVATE_ACCOUNT)) {
            statement.setString(1, token);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error activating account");
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean verifyAccount(int id) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(VERIFY_ACCOUNT)) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error blocking user");
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean loginExists(String username) throws DaoException {
        boolean result = false;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(USERNAME_EXISTS)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            logger.error("Error selecting login");
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean emailExists(String email) throws DaoException {
        boolean result = false;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(EMAIL_EXISTS)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            logger.error("Error selecting email");
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean blockUser(int id) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
        PreparedStatement statement = connection.prepareStatement(BLOCK_USER)) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error blocking user");
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean unblockUser(int id) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UNBLOCK_USER)) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error unblocking user");
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean cashIn(int id, BigDecimal money) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
        PreparedStatement statement = connection.prepareStatement(CASH_IN)) {
            statement.setBigDecimal(1, money);
            statement.setInt(2, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error cashing in", e);
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean withdrawMoney(int id, BigDecimal money) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(WITHDRAW_MONEY)) {
            statement.setBigDecimal(1, money);
            statement.setInt(2, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error withdrawing money from: {}", id, e);
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean uploadScan(String scan, int id) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPLOAD_SCAN)) {
            statement.setString(1, scan);
            statement.setInt(2, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error uploading scan to: {}", id, e);
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Optional<User> findById(long id) throws DaoException {
        Optional<User> user;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            ResultSet resultSet = statement.executeQuery(SELECT_USER_BY_ID);
            User userTemp = new User();
            while (resultSet.next()) {
                setUserFields(resultSet, userTemp);
                userTemp.setPassword(resultSet.getString(DatabaseColumn.PASSWORD));
            }
            user = Optional.of(userTemp);
        } catch (SQLException e) {
            logger.error("Can't find user with id", e);
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID)) {
            statement.setLong(1, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Can't delete user by id");
            throw new DaoException(e);
        }
        return result;
    }

    private void setUserFields(ResultSet resultSet, User user) throws SQLException {
        user.setId(resultSet.getInt(DatabaseColumn.ID));
        user.setUsername(resultSet.getString(DatabaseColumn.USERNAME));
        user.setFirstName(resultSet.getString(DatabaseColumn.FIRST_NAME));
        user.setLastName(resultSet.getString(DatabaseColumn.LAST_NAME));
        user.setEmail(resultSet.getString(DatabaseColumn.EMAIL));
        user.setDateOfBirth(resultSet.getDate(DatabaseColumn.DATE_OF_BIRTH).toLocalDate());
        user.setRole(resultSet.getString(DatabaseColumn.ROLE));
        user.setMoneyBalance(resultSet.getBigDecimal(DatabaseColumn.MONEY_BALANCE));
        user.setPassportScan(resultSet.getString(DatabaseColumn.PASSPORT_SCAN));
        user.setStatusType(resultSet.getString(DatabaseColumn.USER_STATUS));
        user.setToken(resultSet.getString(DatabaseColumn.TOKEN));
    }
}
