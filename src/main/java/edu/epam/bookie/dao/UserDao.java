package edu.epam.bookie.dao;

import edu.epam.bookie.exception.UserDaoException;
import edu.epam.bookie.model.User;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {
    Optional<User> findUserByUsername(String username) throws UserDaoException;
    Optional<User> findUserByUsernameAndPassword(String username, String password) throws UserDaoException;
    Optional<String> findEmailById(int id) throws UserDaoException;
    boolean activateAccount(String username) throws UserDaoException;
}
