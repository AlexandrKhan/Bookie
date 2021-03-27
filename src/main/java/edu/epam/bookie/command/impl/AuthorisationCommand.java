package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class AuthorisationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.AUTHORISATION.getDirectUrl();
    }
}
