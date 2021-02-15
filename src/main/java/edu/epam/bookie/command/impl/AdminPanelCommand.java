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
import java.util.ArrayList;
import java.util.List;

public class AdminPanelCommand implements Command {
    private Logger logger = LogManager.getLogger(AdminPanelCommand.class);
    private static final UserServiceImpl service = UserServiceImpl.INSTANCE;
    @Override
    public String execute(HttpServletRequest request) {
        List<User> users;
        try {
            users = service.findAll();
            request.setAttribute(RequestParameter.USERS, users);
        } catch (UserServiceException e) {
            logger.error("Cant collect all users");
        }
        return PagePath.ADMIN;
    }
}
