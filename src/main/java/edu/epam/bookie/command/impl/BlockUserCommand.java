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
 * Command to block user
 */
public class BlockUserCommand implements Command {
    public static final Logger logger = LogManager.getLogger(BlockUserCommand.class);
    private final UserServiceImpl service = UserServiceImpl.userService;

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.ID);
        String days = request.getParameter(RequestParameter.DAYS);
        String message = request.getParameter(RequestParameter.MESSAGE);
        try {
            int userId = Integer.parseInt(id);
            int daysOfBan = Integer.parseInt(days);
            service.blockUser(userId, daysOfBan, message);
            logger.info("Command block executed");
        } catch (ServiceException e) {
            logger.error("Block user command error");
        }
        return PagePath.ADMIN.getServletPath();
    }
}
