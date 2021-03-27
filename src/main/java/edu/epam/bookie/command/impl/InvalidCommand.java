package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class InvalidCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.ERROR_404.getDirectUrl();
    }
}
