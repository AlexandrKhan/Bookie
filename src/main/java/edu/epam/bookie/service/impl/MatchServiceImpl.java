package edu.epam.bookie.service.impl;

import edu.epam.bookie.dao.impl.MatchDaoImpl;
import edu.epam.bookie.exception.MatchServiceException;
import edu.epam.bookie.exception.UserDaoException;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.Team;
import edu.epam.bookie.service.MatchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatchServiceImpl implements MatchService {
    public static final MatchServiceImpl INSTANCE = new MatchServiceImpl();
    private static final Logger logger = LogManager.getLogger(MatchServiceImpl.class);
    private static final MatchDaoImpl matchDao = MatchDaoImpl.INSTANCE;

    private MatchServiceImpl() {
    }

    @Override
    public List<Match> findAll() throws MatchServiceException {
        Optional<List<Match>> matchList = Optional.empty();
        List<Match> matches = new ArrayList<>();
        try {
            matchList = matchDao.findAll();
        } catch (UserDaoException e) {
            logger.error("Cant find all matches");
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
        } catch (UserDaoException e) {
            logger.error("Cant create match");
        }
        return match;
    }
}
