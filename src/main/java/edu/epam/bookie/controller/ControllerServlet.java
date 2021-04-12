package edu.epam.bookie.controller;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.CommandFactory;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller")
public class ControllerServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ControllerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = processRequest(req);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = processRequest(req);
        resp.sendRedirect(page);
    }

    private String processRequest(HttpServletRequest request) throws ServletException, IOException {
        logger.info("URI: " + request.getRequestURI() + ", content type: " + request.getContentType() + ", method: " + request.getMethod());
        String commandName = request.getParameter(RequestParameter.COMMAND);
        Command command = CommandFactory.defineCommand(commandName);
        return command.execute(request);
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}
