package edu.epam.bookie.dao;

import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.User;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    /**
     * Select user by username and password
     *
     * @param username username
     * @param password password
     * @return user
     * @throws DaoException dao exception
     */
    Optional<User> findUserByUsernameAndPassword(String username, String password) throws DaoException;

    /**
     * Set user status as 'ACTIVATED'
     *
     * @param token token
     * @return boolean
     * @throws DaoException dao exception
     */
    boolean activateAccount(String token) throws DaoException;

    /**
     * Set user status as 'VERIFIED'
     *
     * @param id user id
     * @return boolean
     * @throws DaoException dao exception
     */
    boolean verifyAccount(int id) throws DaoException;

    /**
     * Check if login is already taken
     *
     * @param username username
     * @return boolean
     * @throws DaoException dao exception
     */
    boolean loginExists(String username) throws DaoException;

    /**
     * Check if this email has been used for registration before
     *
     * @param email email
     * @return boolean
     * @throws DaoException dao exception
     */
    boolean emailExists(String email) throws DaoException;

    /**
     * Set user status as 'BLOCKED'
     *
     * @param id user id
     * @return boolean
     * @throws DaoException dao exception
     */
    boolean blockUser(int id) throws DaoException;

    /**
     * Set user status as 'VERIFIED'
     *
     * @param id user id
     * @return boolean
     * @throws DaoException dao exception
     */
    boolean unblockUser(int id) throws DaoException;

    /**
     * Add money to user
     *
     * @param id user id
     * @param money money (cash in or bet payment)
     * @return boolean
     * @throws DaoException dao exception
     */
    boolean cashIn(int id, BigDecimal money) throws DaoException;

    /**
     * Take money from user
     *
     * @param id user id
     * @param money money (bet amount)
     * @return boolean
     * @throws DaoException dao exception
     */
    boolean withdrawMoney(int id, BigDecimal money) throws DaoException;

    /**
     * Upload passport scan
     * @param scan scan name
     * @param id user id
     * @return boolean
     * @throws DaoException dao exception
     */
    boolean uploadScan(String scan, int id) throws DaoException;

}
