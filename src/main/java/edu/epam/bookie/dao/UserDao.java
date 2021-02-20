package edu.epam.bookie.dao;

import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.User;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {
    Optional<User> findUserByUsername(String username) throws DaoException;
    Optional<User> findUserByUsernameAndPassword(String username, String password) throws DaoException;
    Optional<String> findEmailById(int id) throws DaoException;
    boolean activateAccount(String username) throws DaoException;
    boolean loginExists(String username) throws DaoException;
    boolean emailExists(String email) throws DaoException;
    boolean blockUser(int id) throws DaoException;
    boolean unblockUser(int id) throws DaoException;
}
