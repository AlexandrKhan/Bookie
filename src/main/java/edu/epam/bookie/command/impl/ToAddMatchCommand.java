package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.model.sport.Team;

import javax.servlet.http.HttpServletRequest;

public class ToAddMatchCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("teams", Team.values());
        return PagePath.ADD_MATCH;
    }
}
