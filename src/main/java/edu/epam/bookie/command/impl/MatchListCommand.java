package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
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
 * Command sends user to the list of all matches
 */
public class MatchListCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MatchListCommand.class);
    private static final MatchServiceImpl service = MatchServiceImpl.matchService;

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Match> matches;
        try {
            matches = service.findAll();
            session.setAttribute(SessionAttribute.MATCHES, matches);
        } catch (ServiceException e) {
            logger.error("Cant find all matches");
            return PagePath.AUTHORISATION.getServletPath();
        }
        return PagePath.MATCHES.getDirectUrl();
    }
}
