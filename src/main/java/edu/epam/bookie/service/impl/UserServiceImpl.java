package edu.epam.bookie.service.impl;

import edu.epam.bookie.dao.impl.UserDaoImpl;
import edu.epam.bookie.exception.UserDaoException;
import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.model.Role;
import edu.epam.bookie.model.StatusType;
import edu.epam.bookie.model.User;
import edu.epam.bookie.service.UserService;
import edu.epam.bookie.util.PasswordEncryption;
import edu.epam.bookie.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    public static final UserServiceImpl INSTANCE = new UserServiceImpl();

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static final UserValidator VALIDATOR = UserValidator.getInstance();
    private static final UserDaoImpl userDao = UserDaoImpl.INSTANCE;

    private UserServiceImpl() {
    }

    @Override
    public List<User> findAll() throws UserServiceException {
        Optional<List<User>> usersTemp = Optional.empty();
        List<User> users = new ArrayList<>();
        try {
            usersTemp = userDao.findAll();
        } catch (UserDaoException e) {
            logger.error("No users found");
        }
        if (usersTemp.isPresent()) {
            users = usersTemp.get();
        }
        return users;
    }

    @Override
    public boolean checkUser(String username, String password) throws UserServiceException {
        try {
            Optional<User> optionalUser = userDao.findUserByUsername(username);
            return optionalUser.filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).isPresent();
        } catch (UserDaoException e) {
            logger.error(e);
            throw new UserServiceException(e);
        }
    }

    @Override
    public boolean registerUser(String username, String firstName, String lastName, String email,
                                String password, LocalDate dateOfBirth, String scan) throws UserServiceException {
        try {
            if (VALIDATOR.isUsername(username) && VALIDATOR.isPassword(password)) {
                if (!userDao.findUserByUsername(username).equals(Optional.empty())) {
                    return false;
                } else {
                    String encryptedPassword = PasswordEncryption.encryptMessage(password);
                    User user = new User(username, firstName, lastName, email, encryptedPassword, dateOfBirth, scan);
                    user.setRole(Role.USER.toString());
                    user.setStatusType(StatusType.NOT_ACTIVATED.name().toUpperCase());
                    user.setMoneyBalance(0.0);
                    return userDao.create(user);
                }
            } else {
                return false;
            }
        } catch (UserDaoException e) {
            logger.error(e);
            throw new UserServiceException(e);
        }
    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) throws UserServiceException {
        Optional<User> user = Optional.empty();
        if (VALIDATOR.isUsername(username) && VALIDATOR.isPassword(password)) {
            try {
                String encryptedPassword = PasswordEncryption.encryptMessage(password);
                user = userDao.findUserByUsernameAndPassword(username, encryptedPassword);
            } catch (UserDaoException e) {
                logger.error("DB error");
            }
        } else {
            logger.error("Service can't find user by username and pass");
        }
        return user;
    }
}
