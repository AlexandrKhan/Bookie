package edu.epam.bookie.service;

import edu.epam.bookie.exception.BetServiceException;
import edu.epam.bookie.model.sport.Bet;

import java.util.List;

public interface BetService {
    List<Bet> selectBetsByMatchId(long id) throws BetServiceException;
    boolean payBets(Bet bet) throws BetServiceException;
    boolean betLost(Bet bet) throws BetServiceException;
}
