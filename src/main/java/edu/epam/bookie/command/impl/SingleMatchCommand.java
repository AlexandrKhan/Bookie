package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.model.Comment;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static edu.epam.bookie.command.RequestParameter.MATCH_ID;

public class SingleMatchCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SingleMatchCommand.class);
    private final MatchServiceImpl service = MatchServiceImpl.matchService;

    @Override
    public String execute(HttpServletRequest request) {
        Match match;
        List<Comment> comments;
        try {
            comments = service.findCommentsForMatch(Long.valueOf(request.getParameter(MATCH_ID)));
            match = service.findById(Long.valueOf(request.getParameter(MATCH_ID)));
            request.setAttribute(RequestParameter.MATCH, match);
            request.setAttribute(RequestParameter.COMMENTS, comments);
        } catch (ServiceException e) {
            logger.error("Error finding single match", e);
        }
        return PagePath.MATCH.getDirectUrl();
    }
}
