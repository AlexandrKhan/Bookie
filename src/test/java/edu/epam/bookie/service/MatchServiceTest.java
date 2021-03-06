package edu.epam.bookie.service;

import edu.epam.bookie.dao.impl.MatchDaoImpl;
import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.Team;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.testng.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.mock;

public class MatchServiceTest {
    private MatchDaoImpl matchDao;
    private MatchServiceImpl matchService;

    @BeforeMethod
    public void setUp() {
        matchDao = mock(MatchDaoImpl.class);
        Whitebox.setInternalState(MatchDaoImpl.class, MatchDaoImpl.matchDao, matchDao);
        matchService = MatchServiceImpl.matchService;
    }

    @Test
    public void testUpdateMatchDate() throws DaoException, ServiceException {
        when(matchDao.updateDateTimeAtNotStartedMatch(anyInt(), any(), any())).thenReturn(true);
        assertTrue(matchService.updateMatchDate(1, LocalDate.now().plusDays(1), LocalTime.now().plusHours(6)));
    }

    @Test
    public void testUpdateMatchDateFalse() throws DaoException, ServiceException {
        Optional<Match> expected = Optional.of(new Match.MatchBuilder().build());
        when(matchDao.updateDateTimeAtNotStartedMatch(anyInt(), any(), any())).thenReturn(true);
        assertFalse(matchService.updateMatchDate(1, LocalDate.now(), LocalTime.now()));
    }

    @Test
    public void testFindMatchesByTeam() throws DaoException, ServiceException {
        Optional<Match> match = Optional.of(new Match.MatchBuilder().build());
        List<Match> expected = Collections.singletonList(match.get());
        when(matchDao.findMatchesByTeam(any())).thenReturn(Optional.of(expected));
        List<Match> actual = matchService.findMatchesByTeam("Team");
        assertEquals(actual, expected);
    }
}
