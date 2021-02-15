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

public class ActivateAccountCommand implements Command{
    public static final Logger logger = LogManager.getLogger(ActivateAccountCommand.class);
    private UserServiceImpl service = UserServiceImpl.INSTANCE;

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        try {
            if (service.activateAccount(username)) {
                return PagePath.LOGIN;
                } else {
                    return PagePath.REGISTER;
                }
        } catch (UserServiceException e) {
            e.printStackTrace();
        }
        return PagePath.HOME;
    }
}
