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

    Optional<User> findByUsername(String username) throws ServiceException;

    List<User> findAll() throws ServiceException;

    List<Match> findAllMatchesOnWhichUserBetByUserId(Long id) throws ServiceException;

    List<Message> findAllMessagesOfUser(int id) throws ServiceException;

    boolean activateAccount(String username) throws ServiceException;

    boolean verifyAccount(int id) throws ServiceException;

    boolean blockUser(int username, int days, String message) throws ServiceException;

    boolean unblockUser(int username) throws ServiceException;

    boolean cashIn(int id, BigDecimal money) throws ServiceException;

    boolean placeBet(Bet bet) throws ServiceException;

    boolean sendMessage(Message message) throws ServiceException;

    boolean uploadScan(String scan, int id) throws ServiceException;


}
