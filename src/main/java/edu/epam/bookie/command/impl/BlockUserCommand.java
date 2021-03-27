package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class BlockUserCommand implements Command {
    public static final Logger logger = LogManager.getLogger(BlockUserCommand.class);
    private UserServiceImpl service = UserServiceImpl.userService;

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.ID);
        try {
            int userId = Integer.parseInt(id);
            service.blockUser(userId);
        } catch (UserServiceException e) {
            logger.error("Block user command error");
        }
        return PagePath.ADMIN.getServletPath();
    }
}
