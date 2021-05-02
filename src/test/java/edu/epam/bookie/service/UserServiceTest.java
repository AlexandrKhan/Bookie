package edu.epam.bookie.service;

import edu.epam.bookie.dao.impl.BetDaoImpl;
import edu.epam.bookie.dao.impl.MessageDaoImpl;
import edu.epam.bookie.dao.impl.UserDaoImpl;
import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.model.Message;
import edu.epam.bookie.model.Role;
import edu.epam.bookie.model.StatusType;
import edu.epam.bookie.model.User;
import edu.epam.bookie.model.sport.Bet;
import edu.epam.bookie.service.impl.UserServiceImpl;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.testng.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.mock;

public class UserServiceTest {
    private UserDaoImpl userDao;
    private BetDaoImpl betDao;
    private MessageDaoImpl messageDao;
    private UserServiceImpl userService;

    @BeforeMethod
    public void setUp() {
        userDao = mock(UserDaoImpl.class);
        betDao = mock(BetDaoImpl.class);
        messageDao = mock(MessageDaoImpl.class);
        Whitebox.setInternalState(UserDaoImpl.class, UserDaoImpl.userDao, userDao);
        Whitebox.setInternalState(BetDaoImpl.class, BetDaoImpl.betDao, betDao);
        Whitebox.setInternalState(MessageDaoImpl.class, MessageDaoImpl.messageDao, messageDao);
        userService = UserServiceImpl.userService;
    }

//    @Test
//    public void testFindByUsernameAndPassword() throws DaoException, ServiceException {
//        Optional<User> expected = Optional.of(new User(1, "admin123", "admin123", "Alexandr", "Khan",
//                "alexandrhan22@gmail.com", Role.ADMIN, LocalDate.of(1995, 6, 28),
//                new BigDecimal(1000), "Passport.jpg", StatusType.VERIFIED, "123456789"));
//        when(userDao.findUserByUsernameAndPassword(anyString(), anyString())).thenReturn(expected);
//        Optional<User> actual = userService.findUserByUsernameAndPassword("admin123", "admin123");
//        assertEquals(actual, expected);
//    }
//
//    @Test
//    public void testRegister() throws DaoException, ServiceException {
//        Optional<User> expected = Optional.of(new User(1, "admin123", "admin123", "Alexandr", "Khan",
//                "alexandrhan22@gmail.com", Role.ADMIN, LocalDate.of(1995, 6, 28),
//                new BigDecimal(1000), "Passport.jpg", StatusType.VERIFIED, "123456789"));
//        when(userDao.create(any())).thenReturn(expected);
//        Optional<User> actual = userService.registerUser("admin123", "Alexandr", "Khan",
//                "alexandrhan22@gmail.com", "admin123", "admin123", LocalDate.of(1995, 6, 28));
//        assertEquals(actual, expected);
//    }

    @Test
    public void testActivate() throws DaoException, ServiceException {
        when(userDao.activateAccount(anyString())).thenReturn(true);
        assertTrue(userService.activateAccount("1"));
    }

    @Test
    public void testVerify() throws DaoException, ServiceException {
        when(userDao.verifyAccount(anyInt())).thenReturn(true);
        assertTrue(userService.verifyAccount(1));
    }

    @Test
    public void testBlock() throws ServiceException, DaoException {
        when(userDao.blockUser(anyInt())).thenReturn(true);
        assertTrue(userService.blockUser(1, 30, "Block"));
    }

    @Test
    public void testCashIn() throws ServiceException, DaoException {
        when(userDao.cashIn(anyInt(), any())).thenReturn(true);
        assertTrue(userService.cashIn(1, new BigDecimal(100)));
    }

    @Test
    public void testPlaceBet() throws DaoException, ServiceException {
        Optional<Bet> expected = Optional.of(new Bet());
        when(betDao.create(new Bet())).thenReturn(expected);
        assertTrue(userService.placeBet(new Bet()));
    }

    @Test
    public void testSendMessage() throws ServiceException, DaoException {
        Optional<Message> message = Optional.of(new Message());
        when(messageDao.create(new Message())).thenReturn(message);
        assertTrue(userService.sendMessage(new Message()));
    }
}
