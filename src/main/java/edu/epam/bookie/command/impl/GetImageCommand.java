package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetImageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String imageName = RequestParameter.PASSPORT_SCAN_NAME;
        String file = request.getServletContext().getRealPath("/") + "/uploads/" + imageName;
        try {
            HttpServletResponse response;
            byte[] image = Files.readAllBytes(Paths.get(file));
//            response.setContentType("image/jpeg");
//            response.setContentLength(image.length);
//            response.getOutputStream().write(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return PagePath.HOME;
    }
}
