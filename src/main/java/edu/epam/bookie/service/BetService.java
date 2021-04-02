package edu.epam.bookie.service;

import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.model.sport.Bet;

import java.util.List;

public interface BetService {
    List<Bet> selectBetsByMatchId(long id) throws ServiceException;
    List<Bet> selectBetsByUserId(long id) throws ServiceException;
    boolean payBets(Bet bet) throws ServiceException;
    boolean betLost(Bet bet) throws ServiceException;
}
