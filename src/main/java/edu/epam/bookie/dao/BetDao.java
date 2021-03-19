package edu.epam.bookie.dao;

import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.sport.Bet;

import java.util.List;
import java.util.Optional;

public interface BetDao extends BaseDao<Bet>{
    boolean payBets(Bet bet) throws DaoException;
    boolean betLost(Bet bet) throws DaoException;
    Optional<List<Bet>> selectBetsByMatchId(Long matchId) throws DaoException;
    Optional<List<Bet>> selectBetsByUserId(Long userId) throws DaoException;
}
