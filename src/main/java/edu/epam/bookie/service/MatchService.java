package edu.epam.bookie.service;

import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.Team;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface MatchService {
    List<Match> findAll() throws ServiceException;

    Match findById(Long id) throws ServiceException;

    Optional<Match> create(Team first, Team second, LocalDate date, LocalTime time, BigDecimal homeCoeff, BigDecimal drawCoeff, BigDecimal awayCoeff) throws ServiceException;

    /**
     * Generates random score (homeTeamGoals & awayTeamGoals)
     * Based on score sets the result of the match (home, draw, away)
     * And sets match progress as over
     * @param id match id
     * @return boolean
     * @throws ServiceException service exception
     */
    boolean generateScore(Long id) throws ServiceException;

    boolean updateMatchDate(Long id, LocalDate date, LocalTime time) throws ServiceException;

    List<Match> findMatchesByTeam(String team) throws ServiceException;

}
