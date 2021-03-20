package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.command.SessionAttribute;
import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.model.Role;
import edu.epam.bookie.model.User;
import edu.epam.bookie.service.impl.UserServiceImpl;
import edu.epam.bookie.validator.SessionAttributeName;
import edu.epam.bookie.validator.ValidationError;
import edu.epam.bookie.validator.ValidationErrorSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final UserServiceImpl userService = UserServiceImpl.userService;

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String username = request.getParameter(RequestParameter.USERNAME);
        String password = request.getParameter(RequestParameter.PASSWORD);
        Map<String, String> parameters = new HashMap<>();
        parameters.put(RequestParameter.USERNAME, username);
        parameters.put(RequestParameter.PASSWORD, password);

        try {
            HttpSession session = request.getSession();
            Optional<User> userTemp = userService.findUserByUsernameAndPassword(username, password);

            if (userTemp.isPresent()) {
                session.setAttribute(SessionAttribute.AUTHORISED, true);
                session.setAttribute(SessionAttribute.CURRENT_USER, userTemp.get());
                if (userTemp.get().getRole() == Role.ADMIN) {
                    page = PagePath.ADMIN;
                } else {
                    page = PagePath.HOME;
                }
            } else {
                ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
                session.setAttribute(SessionAttributeName.LOGIN_MAP, parameters);
                request.setAttribute(SessionAttributeName.ERROR_SET, errorSet.getAllAndClear());
                page = PagePath.LOGIN;
            }
        } catch (UserServiceException e) {
            logger.error(e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
