package edu.epam.bookie.service.impl;

import edu.epam.bookie.dao.impl.MatchDaoImpl;
import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.model.Comment;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.Result;
import edu.epam.bookie.model.sport.Team;
import edu.epam.bookie.service.MatchService;
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
        Optional<List<Match>> matchList = Optional.empty();
        List<Match> matches = new ArrayList<>();
        try {
            matchList = matchDao.findAll();
        } catch (DaoException e) {
            logger.error("Cant find all matches",e);
        }
        if (matchList.isPresent()) {
            matches = matchList.get();
        }
        return matches;
    }

    @Override
    public Match findById(Long id) throws ServiceException {
        Optional<Match> matchTemp = Optional.empty();
        Match match = new Match();
        try {
            matchTemp = matchDao.findById(id);
        } catch (DaoException e) {
            logger.error("Cant find match by id: {}", id);
        }
        if (matchTemp.isPresent()) {
            match = matchTemp.get();
        }
        return match;
    }

    @Override
    public boolean updateMatchDate(Long id, LocalDate date, LocalTime time) throws ServiceException {
        boolean result = false;
        if (date.isAfter(LocalDate.now()) ||  (!date.isBefore(LocalDate.now()) && time.isAfter(LocalTime.now().plusMinutes(60)))) {
            try {
                result = matchDao.updateDateTimeAtNotStartedMatch(id, date, time);
            } catch (DaoException e) {
                logger.error("Can't update date at match", e);
            }
        } else {
            logger.error("Validation shit on upodate amtch");
            ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
            errorSet.add(ValidationError.BAD_DATE_FOR_MATCH);
        }
        return result;
    }

    @Override
    public List<Match> findMatchesByTeam(String team) throws ServiceException {
        Optional<List<Match>> matchList = Optional.empty();
        List<Match> matches = new ArrayList<>();
        try {
            matchList = matchDao.findMatchesByTeam(team);
        } catch (DaoException e) {
            logger.error("Cant find matches by team", e);
        }
        if (matchList.isPresent()) {
            matches = matchList.get();
        }
        return matches;
    }

    @Override
    public List<Comment> findCommentsForMatch(Long id) throws ServiceException {
        Optional<List<Comment>> commentList = Optional.empty();
        List<Comment> comments = new ArrayList<>();
        try {
            commentList = matchDao.findCommentsForMatch(id);
        } catch (DaoException e) {
            logger.error("Can't get comments for match", e);
        }
        if (commentList.isPresent()) {
            comments = commentList.get();
        }
        return comments;
    }

    @Override
    public Optional<Match> create(Team first, Team second, LocalDate date, LocalTime time, BigDecimal homeCoeff, BigDecimal drawCoeff, BigDecimal awayCoeff) throws ServiceException {
        Match matchTemp = new Match(first, second, date, time, homeCoeff, drawCoeff, awayCoeff);
        Optional<Match> match = Optional.empty();
        try {
            match = matchDao.create(matchTemp);
        } catch (DaoException e) {
            logger.error("Cant create match",e);
        }
        return match;
    }

    @Override
    public boolean generateScoreResultAndEndMatchById(Long id) throws ServiceException {
        boolean result = false;
        int firstTeamGoal = (int) (6.0 * Math.random());
        int secondTeamGoal = (int) (6.0 * Math.random());
        Result matchResult = calculateResult(firstTeamGoal, secondTeamGoal);
            try {
                result = matchDao.setGoalsResultAndOverMatchById(id, firstTeamGoal, secondTeamGoal, matchResult);
            } catch (DaoException e) {
                logger.error("Cant set score by id: {}", id);
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
