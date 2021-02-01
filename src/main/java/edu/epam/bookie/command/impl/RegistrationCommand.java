package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);
    private static final UserServiceImpl userService = UserServiceImpl.INSTANCE;

    @Override
    public String execute(HttpServletRequest request) {
        try {
            String username = request.getParameter(RequestParameter.USERNAME);
            String password = request.getParameter(RequestParameter.PASSWORD);

            if (userService.registerUser(username, password)) {
                return PagePath.LOGIN;
            } else {
                logger.info("Invalid email or password");
                return PagePath.REGISTER;
            }
        } catch (UserServiceException e) {
            logger.error(e);
            throw new RuntimeException();
        }
    }
}
