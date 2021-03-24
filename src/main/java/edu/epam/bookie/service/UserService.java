package edu.epam.bookie.service;

import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.model.Message;
import edu.epam.bookie.model.User;
import edu.epam.bookie.model.sport.Bet;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll() throws UserServiceException;
    List<Message> findAllMessagesOfUser(int id) throws UserServiceException;

    boolean checkUser(String username, String password) throws UserServiceException;

    Optional<User> registerUser(String username, String firstName, String lastName, String email, String password, String repeatPassword, LocalDate dateOfBirth, String scan) throws UserServiceException;

    Optional<User> findUserByUsernameAndPassword(String username, String password) throws UserServiceException;
    Optional<User> findUserById(int id) throws UserServiceException;

    boolean activateAccount(String username) throws UserServiceException;

    Optional<String> findEmailById(String id) throws UserServiceException;

    boolean blockUser(int username) throws UserServiceException;

    boolean unblockUser(int username) throws UserServiceException;

    boolean cashIn(int id, BigDecimal money) throws UserServiceException;
    boolean withdrawMoney(int id, BigDecimal money) throws UserServiceException;

    boolean placeBet(Bet bet) throws UserServiceException;
    boolean addMessage(Message message) throws UserServiceException;


}
