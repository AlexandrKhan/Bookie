package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.model.sport.Team;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Command sends user to page add match
 */
public class ToAddMatchCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.ADD_MATCH.getDirectUrl();
    }
}
