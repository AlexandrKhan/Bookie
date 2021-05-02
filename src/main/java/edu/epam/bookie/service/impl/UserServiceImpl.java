package edu.epam.bookie.service.impl;

import edu.epam.bookie.dao.impl.BetDaoImpl;
import edu.epam.bookie.dao.impl.MatchDaoImpl;
import edu.epam.bookie.dao.impl.MessageDaoImpl;
import edu.epam.bookie.dao.impl.UserDaoImpl;
import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.exception.DaoException;
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
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static edu.epam.bookie.service.impl.BetServiceImpl.betService;
import static edu.epam.bookie.service.impl.MatchServiceImpl.matchService;

public class UserServiceImpl implements UserService {
    public static final UserServiceImpl userService = new UserServiceImpl();
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static final UserDaoImpl userDao = UserDaoImpl.userDao;
    private static final BetDaoImpl betDao = BetDaoImpl.betDao;
    private static final MessageDaoImpl messageDao = MessageDaoImpl.messageDao;
    private static final MatchDaoImpl matchDao = MatchDaoImpl.matchDao;
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(8);
    private static final String UNBAN_MESSAGE = "You are unbanned! Please comply to our rules in order to avoid future bans";
    private static final String DELAY_MESSAGE = "Match: %s - %s was delayed. New time: %s, %s";

    private UserServiceImpl() {
    }

    @Override
    public Optional<User> findById(int id) throws ServiceException {
        Optional<User> user;
        try {
            user = userDao.findById(id);
        } catch (DaoException e) {
            logger.error("Can't find user by id");
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) throws ServiceException {
        Optional<User> user;
        try {
            ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
            String encryptedPassword = PasswordEncryption.encryptMessage(password);
            user = userDao.findUserByUsernameAndPassword(username, encryptedPassword);
            if (!user.isPresent()) {
                errorSet.add(ValidationError.WRONG_LOGIN_OR_PASSWORD);
            }
            logger.info("Login result: {}", user.isPresent());
        } catch (DaoException e) {
            logger.error("Can't find user with username and password");
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        Optional<List<User>> usersTemp;
        List<User> users = new ArrayList<>();
        try {
            usersTemp = userDao.findAll();
        } catch (DaoException e) {
            logger.error("No users found");
            throw new ServiceException(e);
        }
        if (usersTemp.isPresent()) {
            users = usersTemp.get();
        }
        return users;
    }

    @Override
    public List<Message> findAllMessagesOfUser(int id) throws ServiceException {
        List<Message> messages;
        try {
            messages = messageDao.findAllMessagesOfUser(id).orElse(new ArrayList<>());
            Collections.reverse(messages);
        } catch (DaoException e) {
            logger.error("No messages found");
            throw new ServiceException(e);
        }
        return messages;
    }

    @Override
    public List<Match> findAllMatchesOnWhichUserBetByUserId(int id) throws ServiceException {
        List<Match> matches;
        try {
            matches = matchDao.findMatchesOnWhichUserBetByUserId(id).orElse(new ArrayList<>());
            Collections.reverse(matches);
        } catch (DaoException e) {
            logger.error("No matches with bets");
            throw new ServiceException(e);
        }
        return matches;
    }



    @Override
    public Optional<User> registerUser(String username, String firstName, String lastName, String email,
                                       String password, String repeatPassword, LocalDate dateOfBirth) throws ServiceException {
        Optional<User> user = Optional.empty();
        try {
            ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
            if(UserValidator.legalAge(dateOfBirth)) {
                logger.info("Not legal age");
                errorSet.add(ValidationError.ILLEGAL_AGE);
                return user;
            }
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
            if (!UserValidator.passwordsMatch(password, repeatPassword)) {
                logger.info("REPEAT");
                errorSet.add(ValidationError.PASSWORDS_DONT_MATCH);
                return user;
            }
            if (UserValidator.isUsername(username) &&
                UserValidator.isEmail(email) &&
                UserValidator.isPassword(password)) {

                String token = String.valueOf(UUID.randomUUID());
                String encryptedPassword = PasswordEncryption.encryptMessage(password);
                User userTemp = new User.UserBuilder()
                        .withUsername(username)
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withEmail(email)
                        .withPassword(encryptedPassword)
                        .withDateOfBirth(dateOfBirth)
                        .withToken(token)
                        .withNewUserValues()
                        .build();
                user = userDao.create(userTemp);
                MailUtility.sendConfirmMessage(email, token);
            } else {
                logger.info("User validation error");
            }
        } catch (DaoException e) {
            logger.error("Can't register user");
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean activateAccount(String token) throws ServiceException {
        boolean result;
        try {
            result = userDao.activateAccount(token);
            if (result) {
                logger.info("Account is activated");
            } else {
                logger.info("Cant find account");
            }
        } catch (DaoException e) {
            logger.error("Service activation account error");
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean verifyAccount(int id) throws ServiceException {
        boolean result;
        try {
            result = userDao.verifyAccount(id);
        } catch (DaoException e) {
            logger.error("Cant verify account");
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean blockUser(int id, int days, String message) throws ServiceException {
        boolean result;
        try {
            result = userDao.blockUser(id);
            if (result) {
                messageDao.create(new Message(id, message, Theme.BAN));
                logger.info("User {} is blocked for {} days", id, days);
                scheduleUnban(id, days);
            } else {
                logger.info("User {} is not found for blocking", id);
            }
        } catch (DaoException e) {
            logger.error("Service error blocking user");
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean unblockUser(int id) throws ServiceException {
        boolean result;
        try {
            result = userDao.unblockUser(id);
            if (result) {
                logger.info("User {} is unblocked", id);
            } else {
                logger.info("User {} is not found for unblocking", id);
            }
        } catch (DaoException e) {
            logger.error("Service error unblocking user");
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean cashIn(int id, BigDecimal money) throws ServiceException {
        boolean result;
        try {
            result = userDao.cashIn(id, money);
            logger.info("User with id: {} got {} money", id, money);
        } catch (DaoException e) {
            logger.error("Error cashing in");
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean placeBet(Bet bet) throws ServiceException {
        boolean result;
        try {
            betDao.create(bet);
            result = userDao.withdrawMoney(bet.getUserId(), bet.getBetAmount());
            logger.info("Placed bet with id: {}, money: {}", bet.getMatchId(), bet.getBetAmount());
        } catch (DaoException e) {
            logger.error("Error placing bet");
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean sendMessage(Message message) throws ServiceException {
        boolean result;
        try {
            messageDao.create(message);
            result = true;
        } catch (DaoException e) {
            logger.error("Cant create message");
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean uploadScan(String scan, int id) throws ServiceException {
        boolean result;
        try {
            result = userDao.uploadScan(scan, id);
        } catch (DaoException e) {
            logger.error("Error uploading scan");
            throw new ServiceException(e);
        }
        return result;
    }

    public void sendMessageAboutChangedTimeToUser(int matchId) throws ServiceException {
        List<Bet> bets = betService.selectBetsByMatchId(matchId);
        bets.stream()
            .map(Bet::getUserId)
            .distinct()
            .forEach(id -> {
            try {
                Optional<Match> matchTemp  = matchService.findById(matchId);
                Match match;
                if (matchTemp.isPresent()) {
                    match = matchTemp.get();
                    userService.sendMessage(new Message(id, String.format(DELAY_MESSAGE,
                            match.getHomeTeam().getName(),
                            match.getAwayTeam().getName(),
                            match.getStartDate(),
                            match.getStartTime()),
                            Theme.DELAY));
                }
            } catch (ServiceException e) {
                logger.error("Error sending messsage about time changed to user: {}", id);
            }
        });
    }

    private void scheduleUnban(int id, int days) {
        scheduler.schedule(() -> {
            try {
                if (unblockUser(id)) {
                    messageDao.create(new Message(id, UNBAN_MESSAGE, Theme.UNBAN));
                    logger.info("Auto unblock user: {}", id);
                }
            } catch (ServiceException | DaoException e) {
                logger.error("Error in scheduled unblocking");
            }
        }, days, TimeUnit.SECONDS);
    }
}
