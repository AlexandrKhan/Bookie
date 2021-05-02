package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.command.SessionAttribute;
import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.model.sport.Team;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import edu.epam.bookie.validator.MatchValidator;
import edu.epam.bookie.validator.ValidationError;
import edu.epam.bookie.validator.ValidationErrorSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Command for creating new match
 */
public class AddMatchCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddMatchCommand.class);
    private static final MatchServiceImpl matchService = MatchServiceImpl.matchService;
    private static final ValidationErrorSet errorSet = ValidationErrorSet.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        Team firstTeam = Team.getValue(request.getParameter(RequestParameter.HOME_TEAM));
        Team secondTeam = Team.getValue(request.getParameter(RequestParameter.AWAY_TEAM));
        LocalDate date = LocalDate.parse(request.getParameter(RequestParameter.START_DATE));
        LocalTime time = LocalTime.parse(request.getParameter(RequestParameter.START_TIME));
        BigDecimal homeCoeff = new BigDecimal(request.getParameter(RequestParameter.HOME_COEFF));
        BigDecimal drawCoeff = new BigDecimal(request.getParameter(RequestParameter.DRAW_COEFF));
        BigDecimal awayCoeff = new BigDecimal(request.getParameter(RequestParameter.AWAY_COEFF));

        if (!MatchValidator.areDifferentTeams(firstTeam, secondTeam)) {
            errorSet.add(ValidationError.BAD_DATE_FOR_MATCH);
            request.setAttribute(SessionAttribute.ERROR_SET, errorSet.getAllAndClear());
            logger.error("Same team");
            return PagePath.ADD_MATCH.getServletPath();
        } else if (!MatchValidator.isValidTimeForMatchUpdate(date, time)) {
            errorSet.add(ValidationError.TWO_SAME_TEAMS);
            request.setAttribute(SessionAttribute.ERROR_SET, errorSet.getAllAndClear());
            logger.error("Bad date");
            return PagePath.ADD_MATCH.getServletPath();
        } else if (!MatchValidator.correctCoeff(homeCoeff, drawCoeff, awayCoeff)) {
            errorSet.add(ValidationError.BAD_COEFF);
            request.setAttribute(SessionAttribute.ERROR_SET, errorSet.getAllAndClear());
            logger.error("Bad coeff");
            return PagePath.ADD_MATCH.getServletPath();
        }

        try {
            matchService.create(firstTeam, secondTeam, date, time, homeCoeff, drawCoeff, awayCoeff);
        } catch (ServiceException e) {
            logger.error(errorSet.getAllAndClear());
        }
        return PagePath.MATCHES.getServletPath();
    }
}
