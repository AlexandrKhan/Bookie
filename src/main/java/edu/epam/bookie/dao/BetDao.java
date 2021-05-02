package edu.epam.bookie.dao;

import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.sport.Bet;

import java.util.List;
import java.util.Optional;

public interface BetDao extends BaseDao<Bet>{
    /**
     * Invoked if user bet has won
     * Pays (bet * coeff) to user
     * Sets bet status as 'WON'
     *
     * @param bet bet
     * @return boolean
     * @throws DaoException dao exception
     */
    boolean payBets(Bet bet) throws DaoException;

    /**
     * Invoked if user bet has lost
     * Sets bet status as 'LOST'
     *
     * @param bet bet
     * @return boolean
     * @throws DaoException dao exception
     */
    boolean betLost(Bet bet) throws DaoException;

    /**
     * Selects all bets from all users for this match
     *
     * @param matchId match id
     * @return list of bets
     * @throws DaoException dao exception
     */
    Optional<List<Bet>> selectBetsByMatchId(int matchId) throws DaoException;

    /**
     * Selects all bets of this user
     *
     * @param userId user id
     * @return list of bets
     * @throws DaoException dao exception
     */
    Optional<List<Bet>> selectBetsByUserId(int userId) throws DaoException;
}
