package edu.epam.bookie.service.impl;

import edu.epam.bookie.dao.impl.UserDaoImpl;
import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.model.Role;
import edu.epam.bookie.model.StatusType;
import edu.epam.bookie.model.User;
import edu.epam.bookie.service.UserService;
import edu.epam.bookie.util.PasswordEncryption;
import edu.epam.bookie.util.mail.MailUtility;
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
    private static final UserDaoImpl userDao = UserDaoImpl.INSTANCE;

    private UserServiceImpl() {
    }

    @Override
    public List<User> findAll() throws UserServiceException {
        Optional<List<User>> usersTemp = Optional.empty();
        List<User> users = new ArrayList<>();
        try {
            usersTemp = userDao.findAll();
        } catch (DaoException e) {
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
        } catch (DaoException e) {
            logger.error(e);
            throw new UserServiceException(e);
        }
    }

    @Override
    public User registerUser(String username, String firstName, String lastName, String email,
                             String password, String repeatPassword, LocalDate dateOfBirth, String scan) throws UserServiceException {
        User user = null;
        try {
            if (!userDao.loginExists(username) && !userDao.emailExists(email)) {
                if (UserValidator.isUsername(username) && UserValidator.isPassword(password) && password.equals(repeatPassword)) {
                    if (password.equals(repeatPassword)) {
                        String encryptedPassword = PasswordEncryption.encryptMessage(password);
                        User userTemp = new User(username, firstName, lastName, email, encryptedPassword, dateOfBirth, scan);
                        userTemp.setRole(Role.USER.toString());
                        userTemp.setStatusType(StatusType.NOT_ACTIVATED.name().toUpperCase());
                        userTemp.setMoneyBalance(0.0);

                        user = userDao.create(userTemp);
                        MailUtility.sendConfirmMessage(email, user.getUsername());
                    }
                }
            } else {
                throw new UserServiceException("Passwords dont match");
            }
        } catch (DaoException e) {
            logger.error(e);
        }
        return user;
    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) throws UserServiceException {
        Optional<User> user = Optional.empty();
        if (UserValidator.isUsername(username) && UserValidator.isPassword(password)) {
            try {
                String encryptedPassword = PasswordEncryption.encryptMessage(password);
                user = userDao.findUserByUsernameAndPassword(username, encryptedPassword);
            } catch (DaoException e) {
                logger.error("DB error");
            }
        } else {
            logger.error("Service can't find user by username and pass");
        }
        return user;
    }

    @Override
    public boolean activateAccount(String username) throws UserServiceException {
        boolean result = false;
        try {
            result = userDao.activateAccount(username);
            if (result) {
                logger.info("Account {} is activated", username);
            } else {
                logger.info("Cant find {} account", username);
            }
        } catch (DaoException e) {
            logger.error("Service activation account error");
        }
        return result;
    }

    @Override
    public Optional<String> findEmailById(String id) throws UserServiceException {
        Optional<String> email = Optional.empty();
        try {
            email = userDao.findEmailById(Integer.parseInt(id));
        } catch (DaoException e) {
            logger.error("Service cant find email by id");
        }
        return email;
    }

    @Override
    public boolean blockUser(int id) throws UserServiceException {
        boolean result = false;
        try {
            result = userDao.blockUser(id);
            if (result) {
                logger.info("User {} is blocked", id);
            } else {
                logger.info("User {} is not found for blocking", id);
            }
        } catch (DaoException e) {
            logger.error("Service error blocking user");
        }
        return result;
    }

    @Override
    public boolean unblockUser(int id) throws UserServiceException {
        boolean result = false;
        try {
            result = userDao.unblockUser(id);
            if (result) {
                logger.info("User {} is unblocked", id);
            } else {
                logger.info("User {} is not found for unblocking", id);
            }
        } catch (DaoException e) {
            logger.error("Service error unblocking user");
        }
        return result;
    }


}
