package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.exception.MatchServiceException;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import edu.epam.bookie.validator.SessionAttributeName;
import edu.epam.bookie.validator.ValidationErrorSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;

import static edu.epam.bookie.command.RequestParameter.MATCH_ID;
import static edu.epam.bookie.command.RequestParameter.START_DATE;
import static edu.epam.bookie.command.RequestParameter.START_TIME;

public class UpdateMatchDateCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateMatchDateCommand.class);
    private MatchServiceImpl service = MatchServiceImpl.matchService;

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();

        Long id = Long.valueOf(request.getParameter(MATCH_ID));
        LocalDate date = LocalDate.parse(request.getParameter(START_DATE));
        LocalTime time = LocalTime.parse(request.getParameter(START_TIME));
        if (!date.isBefore(LocalDate.now()) && !time.isBefore(LocalTime.now())) {
            try {
                service.updateMatchDate(id, date, time);
                page = PagePath.MATCHES;
            } catch (MatchServiceException e) {
                logger.error("Can't update match date", e);
            }
        } else {
            ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
            request.setAttribute(RequestParameter.MATCH_ID, request.getParameter(RequestParameter.MATCH_ID));
            session.setAttribute(SessionAttributeName.ERROR_SET, errorSet.getAllAndClear());
            page = PagePath.UPDATE_MATCH;
        }
        return page;
    }
}
