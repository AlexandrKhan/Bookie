package edu.epam.bookie.service;

import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.model.Message;
import edu.epam.bookie.model.User;
import edu.epam.bookie.model.sport.Bet;
import edu.epam.bookie.model.sport.Match;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(int id) throws ServiceException;

    Optional<User> registerUser(String username, String firstName, String lastName, String email, String password, String repeatPassword, LocalDate dateOfBirth) throws ServiceException;

    Optional<User> findUserByUsernameAndPassword(String username, String password) throws ServiceException;

    List<User> findAll() throws ServiceException;

    /**
     * All matches on which user placed bets
     *
     * @param id user id
     * @return list of matches
     * @throws ServiceException exception
     */
    List<Match> findAllMatchesOnWhichUserBetByUserId(int id) throws ServiceException;

    /**
     * All user messages
     *
     * @param id user is
     * @return list of messages
     * @throws ServiceException exception
     */
    List<Message> findAllMessagesOfUser(int id) throws ServiceException;

    /**
     * Activate user account
     *
     * @param username username
     * @return result
     * @throws ServiceException exception
     */
    boolean activateAccount(String username) throws ServiceException;

    /**
     * Verify user account
     *
     * @param id user id
     * @return result
     * @throws ServiceException exception
     */
    boolean verifyAccount(int id) throws ServiceException;

    /**
     * Change user status to BLOCKED
     *
     * @param id user id
     * @param days for how long
     * @param message ban reason
     * @return result
     * @throws ServiceException exception
     */
    boolean blockUser(int id, int days, String message) throws ServiceException;

    /**
     * Change user status to VERIFIED
     *
     * @param id user id
     * @return result
     * @throws ServiceException exception
     */
    boolean unblockUser(int id) throws ServiceException;

    /**
     * Add money to user
     *
     * @param id user id
     * @param money money
     * @return result
     * @throws ServiceException exception
     */
    boolean cashIn(int id, BigDecimal money) throws ServiceException;

    /**
     * Plae bet on match
     *
     * @param bet bet
     * @return result
     * @throws ServiceException exception
     */
    boolean placeBet(Bet bet) throws ServiceException;

    /**
     * Send message to user
     *
     * @param message message
     * @return result
     * @throws ServiceException exception
     */
    boolean sendMessage(Message message) throws ServiceException;

    /**
     * Upload passport scan
     *
     * @param scan image name
     * @param id user id
     * @return result
     * @throws ServiceException exception
     */
    boolean uploadScan(String scan, int id) throws ServiceException;


}
