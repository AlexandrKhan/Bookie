package edu.epam.bookie.controller.filter;

import edu.epam.bookie.command.SessionAttribute;
import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.model.User;
import edu.epam.bookie.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Filter updates user info in session on every command execution
 */

@WebFilter(urlPatterns = {"/controller"})
public class UserUpdateFilter implements Filter {
    private static final UserServiceImpl service = UserServiceImpl.userService;
    private static final Logger logger = LogManager.getLogger(UserUpdateFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute(SessionAttribute.CURRENT_USER);
        Optional<User> updatedUser = Optional.empty();
        if (user != null) {
            try {
                updatedUser = service.findById(user.getId());
            } catch (ServiceException e) {
                logger.error("User not found");
            }
            updatedUser.ifPresent(value -> session.setAttribute(SessionAttribute.CURRENT_USER, value));
        }
        chain.doFilter(request, response);
    }
}
