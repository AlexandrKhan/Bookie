package edu.epam.bookie.util.mail;

import edu.epam.bookie.exception.PropertyReaderException;
import edu.epam.bookie.util.PropertiesPath;
import edu.epam.bookie.util.PropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class MailUtility {
    private static final Logger logger = LogManager.getLogger(MailUtility.class);
    private static final String MESSAGE_TEXT = "Your confirmation link: \n%s";
    private static final String MESSAGE_SUBJECT = "Email confirmation";
    private static final String CONFIRMATION_LINK = "http://localhost:8081/controller?command=activate_account&username=%s";

    private MailUtility() {
    }

    public static void sendConfirmMessage(String email, String username) {
        String path = PropertiesPath.MAIL_PROPERTIES;
        String confirmLink = prepareConfirmLink(username);
        String text = String.format(MESSAGE_TEXT, confirmLink);
        try {
            Properties properties = PropertiesReader.readProperties(path);
            MailSender sender = new MailSender(email, MESSAGE_SUBJECT, text, properties);
            sender.send();
            logger.info("Confirmation link was sent to '{}'", email);
        } catch (PropertyReaderException e) {
            logger.error("Error reading email properties, link to {} was not sent", email, e);
        }
    }

    public static boolean sendMessage(String email, String subject, String message) {
        String path = PropertiesPath.MAIL_PROPERTIES;
        boolean result;
        try {
            Properties properties = PropertiesReader.readProperties(path);
            MailSender sender = new MailSender(email, subject, message, properties);
            sender.send();
            logger.info("Message was successfully sent to '{}'", email);
            result = true;
        } catch (PropertyReaderException e) {
            logger.error("Error reading email properties, message to {} was not sent", email, e);
            result = false;
        }
        return result;
    }

    private static String prepareConfirmLink(String username) {
        return String.format(CONFIRMATION_LINK, username);
    }

}
