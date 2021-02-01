package edu.epam.bookie.command.impl;

import edu.epam.bookie.command.Command;
import edu.epam.bookie.command.PagePath;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

public class FileUploadCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FileUploadCommand.class);
    private static final String FILE_TYPE = "image/jpg, image/png, image/gif, image/jpeg";
    private static final FileItemFactory FILE_FACTORY = new DiskFileItemFactory();
    private static final ServletFileUpload FILE_UPLOAD = new ServletFileUpload(FILE_FACTORY);

    @Override
    public String execute(HttpServletRequest request) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            FILE_UPLOAD.setFileSizeMax(1024 * 1024 * 5);
            try {
                List<FileItem> items = FILE_UPLOAD.parseRequest(request);
                for (FileItem item : items) {

                    //Filter only images
                    if (!FILE_TYPE.contains(item.getContentType())) {
                        throw new FileUploadException("Wrong type");
                    }

                    if (!item.isFormField()) {
                        String fileName = item.getName();
                        String root = request.getServletContext().getRealPath("/");
                        File path = new File(root + "/uploads/" + fileName);
                        try {
                            item.write(path);
                        } catch (Exception e) {
                            logger.error("File write error");
                        }
                    }
                }
            } catch (FileUploadException e) {
                logger.error("File upload error " + e);
            }
        }
        return PagePath.HOME;
    }
}
