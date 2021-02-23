package edu.epam.bookie.dao;

import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.sport.Bet;

public interface BetDao extends BaseDao<Bet>{
    boolean placeBet(Bet bet) throws DaoException;
}
