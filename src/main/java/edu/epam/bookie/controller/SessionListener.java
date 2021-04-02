package edu.epam.bookie.controller;

import edu.epam.bookie.command.SessionAttribute;
import edu.epam.bookie.model.sport.Result;
import edu.epam.bookie.model.sport.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static final Logger logger = LogManager.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute(SessionAttribute.TEAMS, Team.values());
        session.setAttribute(SessionAttribute.RESULT, Result.values());
        logger.info("Session created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.invalidate();
    }
}
