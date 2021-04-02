package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.model.User;
import edu.epam.bookie.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
public class AdminPanelCommand implements Command {
    private Logger logger = LogManager.getLogger(AdminPanelCommand.class);
    private static final UserServiceImpl service = UserServiceImpl.userService;
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<User> users;
        try {
            users = service.findAll();
            session.setAttribute(RequestParameter.USERS, users);
        } catch (ServiceException e) {
            logger.error("Cant collect all users");
        }
        return PagePath.ADMIN.getDirectUrl();
    }
}
