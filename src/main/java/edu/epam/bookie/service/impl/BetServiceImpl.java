package edu.epam.bookie.service.impl;

import edu.epam.bookie.dao.impl.BetDaoImpl;
import edu.epam.bookie.exception.BetServiceException;
import edu.epam.bookie.exception.DaoException;
import edu.epam.bookie.model.sport.Bet;
import edu.epam.bookie.service.BetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BetServiceImpl implements BetService {
    public static final BetServiceImpl betService = new BetServiceImpl();
    private static final Logger logger = LogManager.getLogger(BetServiceImpl.class);
    private static final BetDaoImpl betDao = BetDaoImpl.betDao;

    private BetServiceImpl() {
    }

    @Override
    public List<Bet> selectBetsByMatchId(long id) throws BetServiceException {
        Optional<List<Bet>> optional = Optional.empty();
        List<Bet> bets = new ArrayList<>();
        try {
            optional = betDao.selectBetsByMatchId(id);
        } catch (DaoException e) {
            logger.error("Error selecting bets by match id: {}", id, e);
        }
        if (optional.isPresent()) {
            bets = optional.get();
        } else System.out.println("No bets found for match");
        return bets;
    }

    @Override
    public boolean payBets(Bet bet) throws BetServiceException {
        boolean result = false;
        try {
            result = betDao.payBets(bet);
        } catch (DaoException e) {
            logger.error("Cant pay bet", e);
        }
        return result;
    }

    @Override
    public boolean betLost(Bet bet) throws BetServiceException {
        boolean result = false;
        try {
            result = betDao.betLost(bet);
        } catch (DaoException e) {
            logger.error("Cant set bet as lost", e);
        }
        return result;
    }
}
