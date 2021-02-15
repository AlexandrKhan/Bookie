package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.model.User;
import edu.epam.bookie.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final UserServiceImpl userService = UserServiceImpl.INSTANCE;

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter(RequestParameter.USERNAME);
        String password = request.getParameter(RequestParameter.PASSWORD);
        Optional<User> userTemp = Optional.empty();
        HttpSession session = request.getSession();

        try {
            userTemp = userService.findUserByUsernameAndPassword(username, password);
        } catch (UserServiceException e) {
            logger.error("Error finding user");
        }

        if (userTemp.isPresent()) {
            session.setAttribute("authorised", true);
            session.setAttribute("userRole", userTemp.get().getRole().name());
            session.setAttribute("user", userTemp.get());
            return PagePath.HOME;
        } else {
            logger.error("Some error logging in");
            session.setAttribute("errorMessage", true);
            return PagePath.LOGIN;
        }
    }
}
