package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.SessionAttribute;
import edu.epam.bookie.exception.BetServiceException;
import edu.epam.bookie.exception.MatchServiceException;
import edu.epam.bookie.model.User;
import edu.epam.bookie.model.sport.Bet;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.service.impl.BetServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static edu.epam.bookie.command.SessionAttribute.CURRENT_USER;

public class PersonalCabinetCommand implements Command{
    private static final Logger logger = LogManager.getLogger(PersonalCabinetCommand.class);
    private final BetServiceImpl service = BetServiceImpl.betService;

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(CURRENT_USER);
        List<Bet> bets;
        try {
            bets = service.selectBetsByUserId(user.getId());
            session.setAttribute(SessionAttribute.MY_BETS, bets);
        } catch (BetServiceException e) {
            logger.error("Cant find all user bets");
        }
        return PagePath.CABINET;
    }
}
