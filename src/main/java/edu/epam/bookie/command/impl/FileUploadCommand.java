package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import edu.epam.bookie.command.RequestParameter;
import edu.epam.bookie.command.SessionAttribute;
import edu.epam.bookie.model.User;
import edu.epam.bookie.service.impl.UserServiceImpl;
import edu.epam.bookie.util.FileNameGenerator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

/**
 * Command for uploading file (passport scan)
 */
public class FileUploadCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FileUploadCommand.class);
    private static final String FILE_TYPE = "image/jpg, image/png, image/gif, image/jpeg";
    private static final FileItemFactory FILE_FACTORY = new DiskFileItemFactory();
    private static final ServletFileUpload FILE_UPLOAD = new ServletFileUpload(FILE_FACTORY);
    private static final UserServiceImpl userService = UserServiceImpl.userService;

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.CURRENT_USER);
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            FILE_UPLOAD.setFileSizeMax(1024 * 1024 * 5);
            try {
                List<FileItem> items = FILE_UPLOAD.parseRequest(request);
                for (FileItem item : items) {
                    if (!FILE_TYPE.contains(item.getContentType())) {
                        throw new FileUploadException("Wrong type");
                    }
                    if (!item.isFormField()) {
                        String fileName = FileNameGenerator.generateName(item.getName());
                        String root = session.getServletContext().getInitParameter("uploadPath");
                        File path = new File(root + "/" + fileName);
                        request.getSession().setAttribute(RequestParameter.PASSPORT_SCAN, fileName);
                        try {
                            item.write(path);
                            if (userService.uploadScan(fileName, user.getId())) {
                                logger.info("Scan uploaded");
                            }
                        } catch (Exception e) {
                            logger.error("File write error", e);
                        }
                    }
                }
            } catch (FileUploadException e) {
                logger.error("File upload error " + e);
            }
        }
        return PagePath.CABINET.getServletPath();
    }
}