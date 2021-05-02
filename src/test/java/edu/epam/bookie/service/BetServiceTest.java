package edu.epam.bookie.service;

import edu.epam.bookie.dao.impl.BetDaoImpl;
import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.model.sport.Bet;
import edu.epam.bookie.service.impl.BetServiceImpl;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.mock;

public class BetServiceTest {
    private BetDaoImpl betDao;
    private BetServiceImpl betService;

    @BeforeMethod
    public void setUp() {
        betDao = mock(BetDaoImpl.class);
        Whitebox.setInternalState(BetDaoImpl.class, BetDaoImpl.betDao, betDao);
        betService = BetServiceImpl.betService;
    }

    @Test
    public void testSelectBetsByMatchId() throws DaoException, ServiceException {
        Optional<Bet> bet = Optional.of(new Bet());
        List<Bet> expected = Collections.singletonList(bet.get());
        when(betDao.selectBetsByMatchId(anyInt())).thenReturn(Optional.of(expected));
        List<Bet> actual = betService.selectBetsByMatchId(1);
        assertEquals(actual, expected);
    }

    @Test
    public void testSelectBetsByUserId() throws DaoException, ServiceException {
        Optional<Bet> bet = Optional.of(new Bet());
        List<Bet> expected = Collections.singletonList(bet.get());
        when(betDao.selectBetsByUserId(anyInt())).thenReturn(Optional.of(expected));
        List<Bet> actual = betService.selectBetsByUserId(1);
        assertEquals(actual, expected);
    }

    @Test
    public void testPayBets() throws DaoException, ServiceException {
        when(betDao.payBets(any())).thenReturn(true);
        assertTrue(betService.payBets(new Bet()));
    }

    @Test
    public void testBetLost() throws DaoException, ServiceException {
        when(betDao.betLost(any())).thenReturn(true);
        assertTrue(betService.betLost(new Bet()));
    }
}
