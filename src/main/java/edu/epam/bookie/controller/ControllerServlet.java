package edu.epam.bookie.controller;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.CommandFactory;
import edu.epam.bookie.connection.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;

@WebServlet(urlPatterns = "/controller")
public class ControllerServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ControllerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("URI: " + request.getRequestURI() + ", content type: " + request.getContentType() + ", method: " + request.getMethod());

        CommandFactory commandFactory = CommandFactory.getInstance();
        Optional<Command> optionalCommand = commandFactory.defineCommand(request);
        Command command = optionalCommand.orElseThrow(NoSuchElementException::new);
        String path = command.execute(request);

        if (path != null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
            requestDispatcher.forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
