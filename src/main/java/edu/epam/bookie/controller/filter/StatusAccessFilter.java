package edu.epam.bookie.controller.filter;

import edu.epam.bookie.command.*;
import edu.epam.bookie.model.Role;
import edu.epam.bookie.model.StatusType;
import edu.epam.bookie.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/controller"})
public class StatusAccessFilter implements Filter {
    private static final CommandStatusMap MAP = CommandStatusMap.getInstance();
    private static final Logger logger = LogManager.getLogger(StatusAccessFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String command = request.getParameter(RequestParameter.COMMAND);
        if (command != null) {
            CommandType commandType = CommandFactory.getCommandType(command);
            StatusType userStatus = getUserStatus(request);
            if (!MAP.hasStatus(commandType, userStatus)) {
                logger.warn("Role filter. User with status {} can't execute {} command", userStatus, commandType);
                response.sendRedirect(request.getContextPath() + PagePath.ERROR_404);
                return;
            }
        } else {
            logger.warn("Status filter, empty command");
        }
        filterChain.doFilter(request, response);
    }

    private StatusType getUserStatus(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return StatusType.BLOCKED;
        } else {
            return user.getStatusType();
        }
    }
}
