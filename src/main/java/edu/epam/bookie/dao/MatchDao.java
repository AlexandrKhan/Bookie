package edu.epam.bookie.dao;

import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.MatchProgress;
import edu.epam.bookie.model.sport.Result;

import java.util.List;
import java.util.Optional;

public interface MatchDao extends BaseDao<Match> {
    Optional<List<Match>> findAllNotStartedMatches() throws DaoException;
    boolean setGoalsAndResultById(Long id, int first, int second, Result result) throws DaoException;
    boolean setMatchProgressOverById(Long id) throws DaoException;
}
