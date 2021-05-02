package edu.epam.bookie.dao;

import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.Result;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface MatchDao extends BaseDao<Match> {
    /**
     * After the score has been generated
     * @see edu.epam.bookie.controller.scheduler.GenerateScoreTask
     * sets goals, result
     * and match progress as 'OVER'
     *
     * @param id id
     * @param home first team goals
     * @param away away team goals
     * @param result result of the match
     * @return boolean
     * @throws DaoException dao exception
     */
    boolean setGoalsResultAndOverMatchById(Long id, int home, int away, Result result) throws DaoException;

    /**
     * Sets new match date and time
     *
     * @param matchId match id
     * @param date new date of the match
     * @param time new time of the match
     * @return boolean
     * @throws DaoException dao exception
     */
    boolean updateDateTimeAtNotStartedMatch(Long matchId, LocalDate date, LocalTime time) throws DaoException;

    /**
     * Selects all matches of this team
     *
     * @param team team
     * @return list of matches
     * @throws DaoException dao exception
     */
    Optional<List<Match>> findMatchesByTeam(String team) throws DaoException;

    /**
     * Selects all matches on which this user placed bets
     *
     * @param id user id
     * @return list of matches
     * @throws DaoException dao exception
     */
    Optional<List<Match>> findMatchesOnWhichUserBetByUserId(Long id) throws DaoException;
}
