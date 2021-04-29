package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.command.SessionAttribute;
import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import edu.epam.bookie.service.impl.UserServiceImpl;
import edu.epam.bookie.validator.MatchValidator;
import edu.epam.bookie.validator.ValidationError;
import edu.epam.bookie.validator.ValidationErrorSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;

import static edu.epam.bookie.command.RequestParameter.*;

/**
 * Command to update match date and time
 */
public class UpdateMatchDateCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateMatchDateCommand.class);
    private final MatchServiceImpl matchService = MatchServiceImpl.matchService;
    private final UserServiceImpl userService = UserServiceImpl.userService;

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        Long id = Long.valueOf(request.getParameter(MATCH_ID));
        LocalDate date = LocalDate.parse(request.getParameter(START_DATE));
        LocalTime time = LocalTime.parse(request.getParameter(START_TIME));
        if (MatchValidator.isValidTimeForMatchUpdate(date, time)) {
            try {
                matchService.updateMatchDate(id, date, time);
                userService.sendMessageAboutChangedTimeToUser(id);
                page = PagePath.MATCHES.getServletPath();
            } catch (ServiceException e) {
                logger.error("Can't update match date", e);
            }
        } else {
            ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
            errorSet.add(ValidationError.BAD_DATE_FOR_MATCH);
            request.setAttribute(RequestParameter.MATCH_ID, request.getParameter(RequestParameter.MATCH_ID));
            request.setAttribute(SessionAttribute.ERROR_SET, errorSet.getAllAndClear());
            page = PagePath.MATCHES.getDirectUrl();
            logger.error("Validation error on update match command");
        }
        return page;
    }
}
