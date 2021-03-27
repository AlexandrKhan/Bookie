package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.SessionAttribute;
import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActivateAccountCommand implements Command{
    public static final Logger logger = LogManager.getLogger(ActivateAccountCommand.class);
    private UserServiceImpl service = UserServiceImpl.userService;


    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(SessionAttribute.USERNAME);
        try {
            if (service.activateAccount(username)) {
                return PagePath.AUTHORISATION.getDirectUrl();
                }
        } catch (UserServiceException e) {
            logger.error("Error activating account", e);
        }
        return PagePath.HOME.getServletPath();
    }
}
