package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.command.SessionAttribute;
import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.model.StatusType;
import edu.epam.bookie.model.User;
import edu.epam.bookie.model.sport.Bet;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.Result;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import edu.epam.bookie.service.impl.UserServiceImpl;
import edu.epam.bookie.validator.ValidationError;
import edu.epam.bookie.validator.ValidationErrorSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Command to place bet
 */
public class PlaceBetCommand implements Command {
    private static final Logger logger = LogManager.getLogger(PlaceBetCommand.class);
    private final UserServiceImpl userService = UserServiceImpl.userService;
    private final MatchServiceImpl matchService = MatchServiceImpl.matchService;

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.CURRENT_USER);

        int userId = user.getId();
        int matchId = Integer.parseInt(request.getParameter(RequestParameter.MATCH_ID));
        BigDecimal money = user.getMoneyBalance();
        BigDecimal betAmount = new BigDecimal(request.getParameter(RequestParameter.BET_AMOUNT));
        Result betOnResult = Result.valueOf(request.getParameter(RequestParameter.BET_ON_RESULT).toUpperCase());
        Bet bet = new Bet();
        Optional<Match> matchTemp;

        if (user.getStatusType().equals(StatusType.VERIFIED)) {
            try {
                matchTemp = matchService.findById(Integer.parseInt(request.getParameter(RequestParameter.MATCH_ID)));
                if (matchTemp.isPresent()) {
                    Match match = matchTemp.get();
                    bet = new Bet(userId, matchId, betAmount, betOnResult);
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
                }
            } catch (ServiceException e) {
                logger.error("Error getting match by id", e);
            }

            try {
                if (betAmount.compareTo(money) > 0) {
                    logger.info("Not enough money for bet");
                    ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
                    errorSet.add(ValidationError.NOT_ENOUGH_MONEY);
                    request.setAttribute(RequestParameter.MATCH_ID, request.getParameter(RequestParameter.MATCH_ID));
                    session.setAttribute(SessionAttribute.ERROR_SET, errorSet.getAllAndClear());
                    return PagePath.MATCHES.getServletPath();
                }

                userService.placeBet(bet);
                user.setMoneyBalance(money.subtract(betAmount));
                session.setAttribute(SessionAttribute.CURRENT_USER, user);
            } catch (ServiceException e) {
                logger.error(e);
            }
        } else {
            logger.error("You are blocked");
            return PagePath.ERROR_404.getDirectUrl();
        }

        return PagePath.MATCHES.getServletPath();
    }
}
