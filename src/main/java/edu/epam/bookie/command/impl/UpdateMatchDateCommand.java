package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.command.SessionAttribute;
import edu.epam.bookie.exception.BetServiceException;
import edu.epam.bookie.exception.MatchServiceException;
import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.model.Message;
import edu.epam.bookie.model.sport.Bet;
import edu.epam.bookie.service.impl.BetServiceImpl;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import edu.epam.bookie.service.impl.UserServiceImpl;
import edu.epam.bookie.validator.ValidationError;
import edu.epam.bookie.validator.ValidationErrorSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static edu.epam.bookie.command.RequestParameter.*;

public class UpdateMatchDateCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateMatchDateCommand.class);
    private MatchServiceImpl matchService = MatchServiceImpl.matchService;
    private BetServiceImpl betService = BetServiceImpl.betService;
    private UserServiceImpl userService = UserServiceImpl.userService;

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        Long id = Long.valueOf(request.getParameter(MATCH_ID));
        LocalDate date = LocalDate.parse(request.getParameter(START_DATE));
        LocalTime time = LocalTime.parse(request.getParameter(START_TIME));
        if (date.isAfter(LocalDate.now()) ||  (!date.isBefore(LocalDate.now()) && time.isAfter(LocalTime.now().plusMinutes(60)))) {
            try {
                matchService.updateMatchDate(id, date, time);
                sendMessageAboutChangedTimeToUser(id);
                page = PagePath.MATCHES.getServletPath();
            } catch (MatchServiceException e) {
                logger.error("Can't update match date", e);
            } catch (BetServiceException e) {
                logger.error("Can't find allbets for match", e);
            }
        } else {
            ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
            errorSet.add(ValidationError.BAD_DATE_FOR_MATCH);
            request.setAttribute(RequestParameter.MATCH_ID, request.getParameter(RequestParameter.MATCH_ID));
            request.setAttribute(SessionAttribute.ERROR_SET, errorSet.getAllAndClear());
            page = PagePath.MATCHES.getDirectUrl();
            logger.error("Validation shit on update match command");
        }
        return page;
    }

    private void sendMessageAboutChangedTimeToUser(Long matchId) throws BetServiceException {
        List<Bet> bets = betService.selectBetsByMatchId(matchId);
        bets.stream()
            .map(Bet::getUserId).forEach(u -> {
            try {
                userService.addMessage(new Message(u, "Match: " + matchId + " was delayed"));
            } catch (UserServiceException e) {
                logger.error("Error sending messsage about time changed to user: {}", u);
            }
        });
    }
}
