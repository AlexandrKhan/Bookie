package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.command.SessionAttribute;
import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.model.Message;
import edu.epam.bookie.model.User;
import edu.epam.bookie.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToMessagesCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ToMessagesCommand.class);
    private final UserServiceImpl service = UserServiceImpl.userService;

    @Override
    public String execute(HttpServletRequest request) {
        List<Message> messageList;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.CURRENT_USER);
        int userId = user.getId();
        try {
            messageList = service.findAllMessagesOfUser(userId);
            session.setAttribute(RequestParameter.MESSAGES, messageList);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return PagePath.MESSAGES.getDirectUrl();
    }
}
