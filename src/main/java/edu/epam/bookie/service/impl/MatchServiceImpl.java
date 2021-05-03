package edu.epam.bookie.service.impl;

import edu.epam.bookie.dao.impl.MatchDaoImpl;
import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.Result;
import edu.epam.bookie.model.sport.Team;
import edu.epam.bookie.service.MatchService;
import edu.epam.bookie.validator.MatchValidator;
import edu.epam.bookie.validator.ValidationError;
import edu.epam.bookie.validator.ValidationErrorSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class MatchServiceImpl implements MatchService {
    private static final Logger logger = LogManager.getLogger(MatchServiceImpl.class);
    public static final MatchServiceImpl matchService = new MatchServiceImpl();
    private static final MatchDaoImpl matchDao = MatchDaoImpl.matchDao;

    private MatchServiceImpl() {
    }

    @Override
    public List<Match> findAll() throws ServiceException {
        Optional<List<Match>> matchList;
        List<Match> matches = new ArrayList<>();
        try {
            matchList = matchDao.findAll();
        } catch (DaoException e) {
            logger.error("Cant find all matches");
            throw new ServiceException(e);
        }
        if (matchList.isPresent()) {
            matches = matchList.get();
        }
        return matches;
    }

    @Override
    public Optional<Match> findById(int id) throws ServiceException {
        Optional<Match> matchTemp;
        try {
            matchTemp = matchDao.findById(id);
        } catch (DaoException e) {
            logger.error("Cant find match by id: {}", id);
            throw new ServiceException(e);
        }
        return matchTemp;
    }

    @Override
    public boolean updateMatchDate(int id, LocalDate date, LocalTime time) throws ServiceException {
        boolean result = false;
        if (MatchValidator.isValidTimeForMatchUpdate(date, time)) {
            try {
                result = matchDao.updateDateTimeAtNotStartedMatch(id, date, time);
            } catch (DaoException e) {
                logger.error("Can't update date at match");
                throw new ServiceException(e);
            }
        } else {
            logger.error("Validation error on update match");
            ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
            errorSet.add(ValidationError.BAD_DATE_FOR_MATCH);
        }
        return result;
    }

    @Override
    public List<Match> findMatchesByTeam(String team) throws ServiceException {
        Optional<List<Match>> matchList;
        List<Match> matches = new ArrayList<>();
        try {
            matchList = matchDao.findMatchesByTeam(team);
        } catch (DaoException e) {
            logger.error("Cant find matches by team");
            throw new ServiceException(e);
        }
        if (matchList.isPresent()) {
            matches = matchList.get();
        }
        return matches;
    }

    @Override
    public List<Match> findMatchesOfDate(LocalDate date) throws ServiceException {
        Optional<List<Match>> matchList;
        List<Match> matches = new ArrayList<>();
        try {
            matchList = matchDao.findMatchesOfDate(date);
        } catch (DaoException e) {
            logger.error("Cant find matches by date");
            throw new ServiceException(e);
        }
        if (matchList.isPresent()) {
            matches = matchList.get();
        }
        return matches;
    }

    @Override
    public Optional<Match> create(Team first, Team second, LocalDate date, LocalTime time, BigDecimal homeCoeff, BigDecimal drawCoeff, BigDecimal awayCoeff) throws ServiceException {
        Optional<Match> match;
        try {
            Match matchTemp = new Match.MatchBuilder()
                    .withHomeTeam(first)
                    .withAwayTeam(second)
                    .withStartDate(date)
                    .withStartTime(time)
                    .withHomeCoeff(homeCoeff)
                    .withDrawCoeff(drawCoeff)
                    .withAwayCoeff(awayCoeff)
                    .build();
            match = matchDao.create(matchTemp);
        } catch (DaoException e) {
            logger.error("Cant create match");
            throw new ServiceException(e);
        }
        return match;
    }

    @Override
    public boolean generateScore(int id) throws ServiceException {
        boolean result;
        int firstTeamGoal = (int) (6.0 * Math.random());
        int secondTeamGoal = (int) (6.0 * Math.random());
        Result matchResult = calculateResult(firstTeamGoal, secondTeamGoal);
            try {
                result = matchDao.setGoalsResultAndOverMatchById(id, firstTeamGoal, secondTeamGoal, matchResult);
            } catch (DaoException e) {
                logger.error("Cant set score by id: {}", id);
                throw new ServiceException(e);
            }
        return result;
    }

    private Result calculateResult(int firstTeamGoal, int secondTeamGoal) {
        int goalDifference = firstTeamGoal - secondTeamGoal;
        Result matchResult;

        if (goalDifference > 0) {
            matchResult = Result.HOME;
        } else if (goalDifference < 0) {
            matchResult = Result.AWAY;
        } else {
            matchResult = Result.DRAW;
        }
        return matchResult;
    }
}
