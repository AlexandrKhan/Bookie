package edu.epam.bookie.service.impl;

import edu.epam.bookie.dao.impl.BetDaoImpl;
import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.sport.Bet;
import edu.epam.bookie.service.BetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BetServiceImpl implements BetService {
    public static final BetServiceImpl betService = new BetServiceImpl();
    private static final Logger logger = LogManager.getLogger(BetServiceImpl.class);
    private static final BetDaoImpl betDao = BetDaoImpl.betDao;

    private BetServiceImpl() {
    }

    @Override
    public List<Bet> selectBetsByMatchId(int id) throws ServiceException {
        Optional<List<Bet>> optional;
        List<Bet> bets = new ArrayList<>();
        try {
            optional = betDao.selectBetsByMatchId(id);
        } catch (DaoException e) {
            logger.error("Error selecting bets by match id: {}", id);
            throw new ServiceException(e);
        }
        if (optional.isPresent()) {
            bets = optional.get();
        } else {
            logger.info("No bets found for match");
        }
        return bets;
    }

    @Override
    public List<Bet> selectBetsByUserId(int id) throws ServiceException {
        Optional<List<Bet>> optional;
        List<Bet> bets = new ArrayList<>();
        try {
            optional = betDao.selectBetsByUserId(id);
        } catch (DaoException e) {
            logger.error("Error selecting bets by user id: {}", id);
            throw new ServiceException(e);
        }
        if (optional.isPresent()) {
            bets = optional.get();
            Collections.reverse(bets);
        } else {
            logger.info("No bets found for user");
        }
        return bets;
    }

    @Override
    public boolean payBets(Bet bet) throws ServiceException {
        boolean result;
        try {
            result = betDao.payBets(bet);
        } catch (DaoException e) {
            logger.error("Can't pay bet");
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean betLost(Bet bet) throws ServiceException {
        boolean result;
        try {
            result = betDao.betLost(bet);
        } catch (DaoException e) {
            logger.error("Cant set bet as lost");
            throw new ServiceException(e);
        }
        return result;
    }
}
