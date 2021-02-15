package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.service.impl.UserServiceImpl;
import edu.epam.bookie.util.PasswordEncryption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);
    private static final UserServiceImpl userService = UserServiceImpl.INSTANCE;

    @Override
    public String execute(HttpServletRequest request) {

        try {
            String username = request.getParameter(RequestParameter.USERNAME);
            String firstName = request.getParameter(RequestParameter.FIRST_NAME);
            String lastName = request.getParameter(RequestParameter.LAST_NAME);
            String email = request.getParameter(RequestParameter.EMAIL);
            String password = request.getParameter(RequestParameter.PASSWORD);
            LocalDate dateOfBirth = LocalDate.parse(request.getParameter(RequestParameter.DATE_OF_BIRTH));
            String passportScan = request.getParameter(RequestParameter.PASSPORT_SCAN_NAME);

            if (userService.registerUser(username, firstName, lastName, email, password, dateOfBirth, passportScan)) {
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
