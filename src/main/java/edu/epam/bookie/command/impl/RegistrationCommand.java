package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;

public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);
    private static final UserServiceImpl userService = UserServiceImpl.INSTANCE;

    @Override
    public String execute(HttpServletRequest request) {
        try {
            String username = request.getParameter(RequestParameter.USERNAME);
            String first_name = request.getParameter(RequestParameter.FIRST_NAME);
            String last_name = request.getParameter(RequestParameter.LAST_NAME);
            String email = request.getParameter(RequestParameter.EMAIL);
            String password = request.getParameter(RequestParameter.PASSWORD);
            String date_of_birth = request.getParameter(RequestParameter.DATE_OF_BIRTH);

            if (userService.registerUser(username, first_name, last_name, email, password, LocalDate.parse(date_of_birth))) {
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
