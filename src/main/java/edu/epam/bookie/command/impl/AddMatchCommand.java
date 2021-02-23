package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.exception.MatchServiceException;
import edu.epam.bookie.model.sport.Team;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddMatchCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddMatchCommand.class);
    private static final MatchServiceImpl matchService = MatchServiceImpl.matchService;
    private static final String WHITESPACE = "\\s";
    private static final String UNDERLINE = "_";

    @Override
    public String execute(HttpServletRequest request) {
        Team firstTeam = nameToTeam(request.getParameter(RequestParameter.HOME_TEAM));
        Team secondTeam = nameToTeam(request.getParameter(RequestParameter.AWAY_TEAM));
        String date = request.getParameter(RequestParameter.START_DATE);
        String time = request.getParameter(RequestParameter.START_TIME);
        String homeCoeff = request.getParameter(RequestParameter.HOME_COEFF);
        String drawCoeff = request.getParameter(RequestParameter.DRAW_COEFF);
        String awayCoeff = request.getParameter(RequestParameter.AWAY_COEFF);
        try {
            matchService.create(firstTeam, secondTeam, LocalDate.parse(date), LocalTime.parse(time),
                    new BigDecimal(homeCoeff), new BigDecimal(drawCoeff), new BigDecimal(awayCoeff));
        } catch (MatchServiceException e) {
            logger.error("Can't create match");
        }
        return PagePath.MATCHES;
    }

    private Team nameToTeam(String name) {
        return Team.valueOf(name.toUpperCase().replaceAll(WHITESPACE, UNDERLINE));
    }
}
