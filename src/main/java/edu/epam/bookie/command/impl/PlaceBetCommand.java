package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.command.SessionAttribute;
import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.model.User;
import edu.epam.bookie.model.sport.Bet;
import edu.epam.bookie.model.sport.Result;
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

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.CURRENT_USER);

        int userId = user.getId();
        String matchId = request.getParameter(RequestParameter.MATCH_ID);
        LocalDate betDate = LocalDate.now();
        LocalTime betTime = LocalTime.now();
        String betAmount = request.getParameter(RequestParameter.BET_AMOUNT);
        String betOnResult = request.getParameter(RequestParameter.BET_ON_RESULT);
        Bet bet = new Bet(userId, Integer.valueOf(matchId), betDate, betTime, new BigDecimal(betAmount), Result.valueOf(betOnResult.toUpperCase()));
        try {
            userService.placeBet(bet);
        } catch (UserServiceException e) {
            logger.error("Error placing bet", e);
        }
        return PagePath.MATCHES;
    }
}
