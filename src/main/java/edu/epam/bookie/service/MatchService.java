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

    Optional<Match> findById(int id) throws ServiceException;

    Optional<Match> create(Team first, Team second, LocalDate date, LocalTime time, BigDecimal homeCoeff, BigDecimal drawCoeff, BigDecimal awayCoeff) throws ServiceException;

    /**
     * Generate random score (homeTeamGoals & awayTeamGoals)
     * Based on score set the result of the match (home, draw, away)
     * And set match progress as over
     *
     * @param id match id
     * @return boolean
     * @throws ServiceException service exception
     */
    boolean generateScore(int id) throws ServiceException;

    /**
     * Update match date
     *
     * @param id match id
     * @param date new Date
     * @param time new Time
     * @return result
     * @throws ServiceException exception
     */
    boolean updateMatchDate(int id, LocalDate date, LocalTime time) throws ServiceException;

    /**
     * Select all matches of this team
     *
     * @param team team
     * @return list of matches
     * @throws ServiceException exception
     */
    List<Match> findMatchesByTeam(String team) throws ServiceException;

}
