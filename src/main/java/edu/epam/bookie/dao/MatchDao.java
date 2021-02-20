package edu.epam.bookie.dao;

import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.sport.Match;

import java.util.List;
import java.util.Optional;

public interface MatchDao extends BaseDao<Match> {
    Optional<List<Match>> findAllNotStartedMatches() throws DaoException;
    boolean setGoalsById(Long id) throws DaoException;
}
