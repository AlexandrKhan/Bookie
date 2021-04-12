package edu.epam.bookie.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for command
 */
public interface Command {
    /**
     * Primary method of each command
     *
     * @param request httprequest
     * @return path to page
     */
    String execute(HttpServletRequest request);
}
