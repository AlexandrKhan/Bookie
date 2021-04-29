package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Command to verify user
 */
public class VerifyAccountCommand implements Command {
    public static final Logger logger = LogManager.getLogger(VerifyAccountCommand.class);
    private final UserServiceImpl service = UserServiceImpl.userService;

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.ID);
        try {
            service.verifyAccount(Integer.parseInt(id));
        } catch (ServiceException e) {
            logger.error("Error verifying user");
        }
        return PagePath.ADMIN.getServletPath();
    }
}
