package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.SessionAttribute;
import edu.epam.bookie.exception.MatchServiceException;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.Result;
import edu.epam.bookie.model.sport.Team;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MatchListCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MatchListCommand.class);
    private final MatchServiceImpl service = MatchServiceImpl.matchService;

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Match> matches;
        try {
            matches = service.findAll();
            session.setAttribute(SessionAttribute.MATCHES, matches);
            session.setAttribute(SessionAttribute.TEAMS, Team.values());
            session.setAttribute(SessionAttribute.RESULT, Result.values());
        } catch (MatchServiceException e) {
            logger.error("Cant find all matches");
        }
        return PagePath.MATCHES;
    }
}
