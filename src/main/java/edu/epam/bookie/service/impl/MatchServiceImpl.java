package edu.epam.bookie.service.impl;

import edu.epam.bookie.dao.impl.MatchDaoImpl;
import edu.epam.bookie.exception.MatchServiceException;
import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.Team;
import edu.epam.bookie.service.MatchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class MatchServiceImpl implements MatchService {
    public static final MatchServiceImpl INSTANCE = new MatchServiceImpl();
    private static final Logger logger = LogManager.getLogger(MatchServiceImpl.class);
    private static final MatchDaoImpl matchDao = MatchDaoImpl.INSTANCE;

    public static Map<Integer,LocalTime> todayMatchStartTimeMap = new HashMap<>();

    private MatchServiceImpl() {
    }

    @Override
    public List<Match> findAll() throws MatchServiceException {
        Optional<List<Match>> matchList = Optional.empty();
        List<Match> matches = new ArrayList<>();
        try {
            matchList = matchDao.findAll();
        } catch (DaoException e) {
            logger.error("Cant find all matches");
        }
        if (matchList.isPresent()) {
            matches = matchList.get();
        }
        return matches;
    }

    @Override
    public List<Match> findAllNotStartedMatches() throws MatchServiceException {
        Optional<List<Match>> matchList = Optional.empty();
        List<Match> matches = new ArrayList<>();
        try {
            matchList = matchDao.findAllNotStartedMatches();
        } catch (DaoException e) {
            logger.error("Cant find all not started matches");
        }
        if (matchList.isPresent()) {
            matches = matchList.get();
        }
        return matches;
    }

    @Override
    public Match create(Team first, Team second, LocalDate date, LocalTime time) throws MatchServiceException {
        Match match = new Match(first,second,date,time);
        try {
            match = matchDao.create(match);
        } catch (DaoException e) {
            logger.error("Cant create match");
        }
        return match;
    }

    @Override
    public boolean deleteById(Long id) throws MatchServiceException {
        boolean result = false;
        try {
            result = matchDao.deleteById(id);
        } catch (DaoException e) {
            logger.error("Cant delete match by id: " + id);
        }
        return result;
    }

    @Override
    public boolean setGoalsById(Long id) throws MatchServiceException {
        boolean result = false;
        try {
            result = matchDao.setGoalsById(id);
        } catch (DaoException e) {
            logger.error("Cant set score by id" + id);
        }
        return result;
    }
}
