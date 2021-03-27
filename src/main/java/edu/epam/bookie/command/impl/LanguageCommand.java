package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements Command {
    private static final String RU = "ru";
    private static final String EN = "en";
    private static final String LANGUAGE = "language";

    @Override
    public String execute(HttpServletRequest request) {
        String value = request.getParameter(RequestParameter.LANGUAGE);
        HttpSession session = request.getSession();
        switch (value) {
            case RU: {
                session.setAttribute(LANGUAGE, RU);
                break;
            }
            case EN: {
                session.setAttribute(LANGUAGE, EN);
                break;
            }
            default: {
                session.setAttribute(LANGUAGE, RU);
            }
        }
        return PagePath.AUTHORISATION.getDirectUrl();
    }
}
