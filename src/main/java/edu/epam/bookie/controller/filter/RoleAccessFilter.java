package edu.epam.bookie.controller.filter;

import edu.epam.bookie.command.*;
import edu.epam.bookie.model.Role;
import edu.epam.bookie.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filters user access to commands by role
 * @see CommandRoleMap
 */
@WebFilter(urlPatterns = {"/controller"})
public class RoleAccessFilter implements Filter {
    private static final CommandRoleMap MAP = CommandRoleMap.getInstance();
    private static final Logger logger = LogManager.getLogger(RoleAccessFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String command = request.getParameter(RequestParameter.COMMAND);
        if (command != null) {
            CommandType commandType = CommandType.getCommandType(command);
            Role userRole = getUserRole(request);
            if (!MAP.hasRole(commandType, userRole)) {
                logger.warn("Role filter. User with role {} can't execute {} command", userRole, commandType);
                response.sendRedirect(request.getContextPath()+ PagePath.ERROR_404);
                return;
            }
        } else {
            logger.warn("Role filter, empty command");
        }
        filterChain.doFilter(request, response);
    }

    private Role getUserRole(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Role.GUEST;
        } else {
            return user.getRole();
        }
    }
}
