package edu.epam.bookie.service;

import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.model.sport.Bet;

import java.util.List;

public interface BetService {
    /**
     * Select all bets for this match
     *
     * @param id match id
     * @return list of bets
     * @throws ServiceException exception
     */
    List<Bet> selectBetsByMatchId(int id) throws ServiceException;

    /**
     * Select all bets of user
     *
     * @param id user id
     * @return list of bets
     * @throws ServiceException exception
     */
    List<Bet> selectBetsByUserId(int id) throws ServiceException;

    /**
     * Pay for bet and change bet status to 'WON"
     *
     * @param bet bet
     * @return result
     * @throws ServiceException exception
     */
    boolean payBets(Bet bet) throws ServiceException;

    /**
     * Change bet status to 'LOST"
     *
     * @param bet bet
     * @return result
     * @throws ServiceException exception
     */
    boolean betLost(Bet bet) throws ServiceException;
}
