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
import java.time.LocalDate;

public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);
    private static final UserServiceImpl userService = UserServiceImpl.INSTANCE;

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = request.getParameter(RequestParameter.USERNAME);
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String repeatPassword = request.getParameter(RequestParameter.REPEAT_PASSWORD);
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter(RequestParameter.DATE_OF_BIRTH));
        String passportScan = request.getParameter(RequestParameter.PASSPORT_SCAN_NAME);

        User user = null;
        try {
            user = userService.registerUser(username, firstName, lastName, email, password, repeatPassword, dateOfBirth, passportScan);
        } catch (UserServiceException e) {
            logger.error("Register error");
        }
        if (user != null) {
            logger.info("New user registered: " + username);
        }
        return PagePath.LOGIN;
    }
}
