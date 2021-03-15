package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;

import javax.servlet.http.HttpServletRequest;

public class ToUpdateMatchCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(RequestParameter.MATCH_ID, request.getParameter(RequestParameter.MATCH_ID));
        return PagePath.UPDATE_MATCH;
    }
}
