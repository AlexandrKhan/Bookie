package edu.epam.bookie.dao;

import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.User;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserDao extends BaseDao<User> {
    Optional<User> findUserByUsername(String username) throws DaoException;
    Optional<User> findUserByUsernameAndPassword(String username, String password) throws DaoException;
    Optional<String> findEmailById(int id) throws DaoException;
    boolean activateAccount(String token) throws DaoException;
    boolean verifyAccount(int id) throws DaoException;
    boolean loginExists(String username) throws DaoException;
    boolean emailExists(String email) throws DaoException;
    boolean blockUser(int id) throws DaoException;
    boolean unblockUser(int id) throws DaoException;
    boolean cashIn(int id, BigDecimal money) throws DaoException;
    boolean withdrawMoney(int id, BigDecimal money) throws DaoException;
}
