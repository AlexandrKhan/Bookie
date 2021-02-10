package edu.epam.bookie.service.impl;

import edu.epam.bookie.dao.impl.UserDaoImpl;
import edu.epam.bookie.exception.UserDaoException;
import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.model.Role;
import edu.epam.bookie.model.User;
import edu.epam.bookie.service.UserService;
import edu.epam.bookie.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    public static final UserServiceImpl INSTANCE = new UserServiceImpl();

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static final UserValidator VALIDATOR = UserValidator.getInstance();
    private static final UserDaoImpl userDao = UserDaoImpl.INSTANCE;

    private UserServiceImpl() {
    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) throws UserServiceException {
        Optional<User> user = Optional.empty();
        if (VALIDATOR.isUsername(username) && VALIDATOR.isPassword(password)) {
            try {
                user = userDao.findUserByUsernameAndPassword(username, password);
            } catch (UserDaoException e) {
                logger.error("DB error");
            }
        }
        return user;
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
     public boolean registerUser(String username, String first_name, String last_name, String email, String password, LocalDate date_of_birth) throws UserServiceException {
         try {
             if (VALIDATOR.isUsername(username) && VALIDATOR.isPassword(password)) {
                 if (!userDao.findUserByUsername(username).equals(Optional.empty())) {
                     return false;
                 } else {
                     User user = new User(username, first_name, last_name, email, password, date_of_birth);
                     user.setRole(Role.USER);
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
}
