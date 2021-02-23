package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.command.SessionAttribute;
import edu.epam.bookie.exception.MatchServiceException;
import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.model.User;
import edu.epam.bookie.model.sport.Bet;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.Result;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import edu.epam.bookie.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class PlaceBetCommand implements Command {
    private static final Logger logger = LogManager.getLogger(PlaceBetCommand.class);
    private final UserServiceImpl userService = UserServiceImpl.userService;
    private final MatchServiceImpl matchService = MatchServiceImpl.matchService;

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.CURRENT_USER);

        int userId = user.getId();
        int matchId = Integer.valueOf(request.getParameter(RequestParameter.MATCH_ID));
        BigDecimal betAmount = new BigDecimal(request.getParameter(RequestParameter.BET_AMOUNT));
        Result betOnResult = Result.valueOf(request.getParameter(RequestParameter.BET_ON_RESULT).toUpperCase());
        Bet bet = new Bet();
        Match match;

        try {
            match = matchService.findById(Long.valueOf(request.getParameter(RequestParameter.MATCH_ID)));
            bet = new Bet(userId, matchId, betAmount,betOnResult);
            switch (betOnResult.getName()) {
                case "Home":
                    bet.setBetCoeff(match.getHomeCoeff());
                    break;
                case "Away":
                    bet.setBetCoeff(match.getAwayCoeff());
                    break;
                default:
                    bet.setBetCoeff(match.getDrawCoeff());
                    break;
            }
        } catch (MatchServiceException e) {
            logger.error("Error getting match by id", e);
        }

        try {
            userService.placeBet(bet);
        } catch (UserServiceException e) {
            logger.error("Error placing bet", e);
        }
        return PagePath.MATCHES;
    }
}
