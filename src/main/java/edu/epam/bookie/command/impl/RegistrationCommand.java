package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.model.User;
import edu.epam.bookie.service.impl.UserServiceImpl;
import edu.epam.bookie.validator.SessionAttributeName;
import edu.epam.bookie.validator.ValidationErrorSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;

public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);
    private static final UserServiceImpl userService = UserServiceImpl.userService;

    @Override
    public String execute(HttpServletRequest request) {
        String page;

        String username = request.getParameter(RequestParameter.USERNAME);
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String repeatPassword = request.getParameter(RequestParameter.REPEAT_PASSWORD);
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter(RequestParameter.DATE_OF_BIRTH));
        String passportScan = request.getParameter(RequestParameter.PASSPORT_SCAN_NAME);

        Optional<User> user = Optional.empty();

        try {
            user = userService.registerUser(username, firstName, lastName, email, password, repeatPassword, dateOfBirth, passportScan);
        } catch (UserServiceException e) {
            logger.error("Register error", e);
        }
        if (user.isPresent()) {
            logger.info("New user registered: " + username);
            page = PagePath.LOGIN;
        } else {
            ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
            request.setAttribute(SessionAttributeName.ERROR_SET, errorSet.getAllAndClear());
            page = PagePath.REGISTER;
        }
        return page;
    }
}
