package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.model.User;
import edu.epam.bookie.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class CashInCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CashInCommand.class);
    private UserServiceImpl service = UserServiceImpl.INSTANCE;

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        BigDecimal money = new BigDecimal(request.getParameter("cashInSum"));
        User user = (User) session.getAttribute("user");
        try {
            int userId = user.getId();
            service.cashIn(userId, money);
        } catch (UserServiceException e) {
            logger.error("Error cashing in", e);
        }
        return PagePath.HOME;
    }
}
