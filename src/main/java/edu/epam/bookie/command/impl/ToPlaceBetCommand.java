package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.model.sport.Result;

import javax.servlet.http.HttpServletRequest;

public class ToPlaceBetCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(RequestParameter.RESULT, Result.values());
        request.setAttribute(RequestParameter.MATCH_ID, request.getParameter(RequestParameter.MATCH_ID));
        return PagePath.PLACE_BET;
    }
}
