package edu.epam.bookie.util.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class MailSender {
    private static final Logger logger = LogManager.getLogger(MailSender.class);
    private static final String ENCODING = "utf-8";
    private final String sendToEmail;
    private final String mailSubject;
    private final String mailText;
    private final Properties properties;
    private MimeMessage message;

    public MailSender(String sendToEmail, String mailSubject, String mailText, Properties properties) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = properties;
    }

    public void send() {
        try {
            initMessage();
            Transport.send(message);
        } catch (AddressException e) {
            logger.error("Invalid email address: ", e);
        } catch (MessagingException e) {
            logger.error("Error generating or sending message: ", e);
        }
    }

    private void initMessage() throws MessagingException {
        Session mailSession;
        mailSession = createSession(properties);
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        message.setFrom("bushwacker148@gmail.com");
        message.setSubject(mailSubject);
        message.setText(mailText, ENCODING);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }

    public Session createSession(Properties properties) {
        String userName = properties.getProperty("mail.user.name");
        String userPassword = properties.getProperty("mail.user.password");
        return Session.getDefaultInstance(properties, new javax.mail.Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName,userPassword);
            }
        });
    }
}
