package edu.epam.bookie.dao;

import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.MatchProgress;
import edu.epam.bookie.model.sport.Result;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface MatchDao extends BaseDao<Match> {
    Optional<List<Match>> findAllNotStartedMatches() throws DaoException;
    boolean setGoalsResultAndOverMatchById(Long id, int first, int second, Result result) throws DaoException;
    boolean updateDateTimeAtNotStartedMatch(Long matchId, LocalDate date, LocalTime time) throws DaoException;
    Optional<List<Match>> findMatchesByTeam(String team) throws DaoException;
    Optional<List<Match>> findMatchesOnWhichUserBetByUserId(Long id) throws DaoException;
}
