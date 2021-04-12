package edu.epam.bookie.util.mail;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SessionFactory {

    public static Session createSession(Properties properties) {
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
