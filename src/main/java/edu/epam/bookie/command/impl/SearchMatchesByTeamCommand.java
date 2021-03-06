package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.command.SessionAttribute;
import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Command allows to search matches by team name
 */
public class SearchMatchesByTeamCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SearchMatchesByTeamCommand.class);
    private final MatchServiceImpl service = MatchServiceImpl.matchService;

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Match> matches;
        try {
            matches = service.findMatchesByTeam(request.getParameter(RequestParameter.TEAM_NAME).toUpperCase());
            session.setAttribute(SessionAttribute.MATCHES, matches);
        } catch (ServiceException e) {
            logger.error("Error in search command", e);
        }
        return PagePath.MATCHES.getDirectUrl();
    }
}
