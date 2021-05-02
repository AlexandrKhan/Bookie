package edu.epam.bookie.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;

/**
 * Servlet gets images (passport scan) from local storage
 */

@WebServlet(name = "showImage", urlPatterns = "/showImage/*")
public class ShowImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String imagePath = request.getServletContext().getInitParameter("uploadPath");
        String requestedImage = request.getPathInfo();
        File image = new File(imagePath, URLDecoder.decode(requestedImage, "UTF-8"));
        String contentType = getServletContext().getMimeType(image.getName());
        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));
        Files.copy(image.toPath(), response.getOutputStream());
    }
}
