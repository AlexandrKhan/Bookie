package edu.epam.bookie.command;

import javax.servlet.http.HttpServletRequest;

public interface Command extends BaseCommand{
    String execute(HttpServletRequest request);
}
