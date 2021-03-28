package edu.epam.bookie.service.impl;

import edu.epam.bookie.dao.impl.BetDaoImpl;
import edu.epam.bookie.dao.impl.MatchDaoImpl;
import edu.epam.bookie.dao.impl.MessageDaoImpl;
import edu.epam.bookie.dao.impl.UserDaoImpl;
import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.model.*;
import edu.epam.bookie.model.sport.Bet;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.service.UserService;
import edu.epam.bookie.util.PasswordEncryption;
import edu.epam.bookie.util.mail.MailUtility;
import edu.epam.bookie.validator.UserValidator;
import edu.epam.bookie.validator.ValidationError;
import edu.epam.bookie.validator.ValidationErrorSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UserServiceImpl implements UserService {
    public static final UserServiceImpl userService = new UserServiceImpl();
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static final UserDaoImpl userDao = UserDaoImpl.userDao;
    private static final BetDaoImpl betDao = BetDaoImpl.betDao;
    private static final MessageDaoImpl messageDao = MessageDaoImpl.messageDao;
    private static final MatchDaoImpl matchDao = MatchDaoImpl.matchDao;
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(8);

    private static final String UNBAN_MESSAGE = "You are unbanned! Please comply to our rules in order to avoid future bans";

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
    public List<Message> findAllMessagesOfUser(int id) throws UserServiceException {
        List<Message> messages = new ArrayList<>();
        try {
            messages = messageDao.findAllMessagesOfUser(id).get();
        } catch (DaoException e) {
            logger.error("No messages found");
        }
        return messages;
    }

    @Override
    public List<Match> findAllMatchesOnWhichUserBetByUserId(Long id) throws UserServiceException {
        List<Match> matches = new ArrayList<>();
        try {
            matches = matchDao.findMatchesOnWhichUserBetByUserId(id).get();
        } catch (DaoException e) {
            logger.error("No matches with bets");
        }
        return matches;
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
    public Optional<User> registerUser(String username, String firstName, String lastName, String email,
                                       String password, String repeatPassword, LocalDate dateOfBirth) throws UserServiceException {
        Optional<User> user = Optional.empty();
        try {
            ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
            if (userDao.loginExists(username)) {
                logger.info("Username");
                errorSet.add(ValidationError.LOGIN_EXISTS);
                return user;
            }
            if (userDao.emailExists(email)) {
                logger.info("EMAIL");
                errorSet.add(ValidationError.EMAIL_EXISTS);
                return user;
            }
            if (!password.equals(repeatPassword)) {
                logger.info("REPEAT");

                errorSet.add(ValidationError.PASSWORDS_DONT_MATCH);
                return user;
            }
            if (UserValidator.isUsername(username) && UserValidator.isEmail(email) && UserValidator.isPassword(password)) {
                String token = String.valueOf(UUID.randomUUID());
                String encryptedPassword = PasswordEncryption.encryptMessage(password);
                User userTemp = new User(username, firstName, lastName, email, encryptedPassword, dateOfBirth, token);
                userTemp.setRole(Role.USER.toString());
                userTemp.setStatusType(StatusType.NOT_ACTIVATED.name().toUpperCase());
                userTemp.setMoneyBalance(BigDecimal.valueOf(0));

                user = userDao.create(userTemp);
                MailUtility.sendConfirmMessage(email, token);
            } else {
                logger.info("VALIDATOR");
            }
        } catch (DaoException e) {
            throw new UserServiceException(e);
        }
        return user;
    }


    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) throws UserServiceException {
        Optional<User> user = Optional.empty();
        try {
            ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
            String encryptedPassword = PasswordEncryption.encryptMessage(password);
            user = userDao.findUserByUsernameAndPassword(username, encryptedPassword);
            if (!user.isPresent()) {
                errorSet.add(ValidationError.WRONG_LOGIN_OR_PASSWORD);
            }
            logger.info("Login result: {}", user.isPresent());
        } catch (DaoException e) {
            logger.error("DB error");
        }
        return user;
    }

    @Override
    public Optional<User> findUserById(int id) throws UserServiceException {
        Optional<User> user = Optional.empty();
        try {
            user = userDao.findById(id);
        } catch (DaoException e) {
            logger.error("Cant find user by id");
        }
        return user;
    }

    @Override
    public boolean activateAccount(String token) throws UserServiceException {
        boolean result = false;
        try {
            result = userDao.activateAccount(token);
            if (result) {
                logger.info("Account is activated");
            } else {
                logger.info("Cant find account");
            }
        } catch (DaoException e) {
            logger.error("Service activation account error");
        }
        return result;
    }

    @Override
    public boolean verifyAccount(int id) throws UserServiceException {
        boolean result = false;
        try {
            result = userDao.verifyAccount(id);
        } catch (DaoException e) {
            logger.error("Cant verify account");
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
    public boolean blockUser(int id, int days, String message) throws UserServiceException {
        boolean result = false;
        try {
            result = userDao.blockUser(id);
            if (result) {
                messageDao.create(new Message(id, message, Theme.BAN));
                logger.info("User {} is blocked", id);
                scheduler.schedule(() -> {
                    try {
                        unblockUser(id);
                        messageDao.create(new Message(id, UNBAN_MESSAGE, Theme.UNBAN));
                        logger.info("auto unblock user");
                    } catch (UserServiceException | DaoException e) {
                        logger.error("Error in scheduled unblocking");
                    }
                }, days, TimeUnit.SECONDS);
            } else {
                logger.info("User {} is not found for blocking", id);
            }
        } catch (DaoException e) {
            logger.error("Service error blocking user", e);
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
            logger.error("Service error unblocking user", e);
        }
        return result;
    }

    @Override
    public boolean cashIn(int id, BigDecimal money) throws UserServiceException {
        boolean result = false;
        try {
            result = userDao.cashIn(id, money);
            logger.info("User with id: {} got {} money", id, money);
        } catch (DaoException e) {
            logger.error("Error cashing in", e);
        }
        return result;
    }

    @Override
    public boolean withdrawMoney(int id, BigDecimal money) throws UserServiceException {
        boolean result = false;
        try {
            result = userDao.withdrawMoney(id, money);
            logger.info("User with id: {} withdrawed {} money", id, money);
        } catch (DaoException e) {
            logger.error("Error withdrawing money in", e);
        }
        return result;
    }

    @Override
    public boolean placeBet(Bet bet) throws UserServiceException {
        boolean result = false;
        try {
            betDao.create(bet);
            result = (userDao.withdrawMoney(bet.getUserId(), bet.getBetAmount()));
            logger.info("Placed bet with id: {}, money: {}", bet.getMatchId(), bet.getBetAmount());
        } catch (DaoException e) {
            logger.error("Error placing bet", e);
        }
        return result;
    }

    @Override
    public boolean addMessage(Message message) throws UserServiceException {
        boolean result = false;
        try {
            messageDao.create(message);
            result = true;
        } catch (DaoException e) {
            logger.error("Cant create message", e);
        }
        return result;
    }


}
