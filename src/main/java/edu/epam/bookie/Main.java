/*
package edu.epam.bookie;

import edu.epam.bookie.connection.ConnectionFactory;
import edu.epam.bookie.connection.ConnectionPool;
import edu.epam.bookie.dao.impl.UserDaoImpl;
import edu.epam.bookie.exception.UserDaoException;
import edu.epam.bookie.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDaoImpl dao = UserDaoImpl.INSTANCE;

        try (Connection connection = ConnectionFactory.getConnection()) {
            Optional<List<User>> users = Optional.empty();
            try {
                users = dao.findAll();
            } catch (UserDaoException e) {
                e.printStackTrace();
            }
            for (User u : users.get()) {
                System.out.println(u.getUsername());
            }
            connection.close();
        }
        User user1 = new User("new","user");
        try(Connection connection = ConnectionFactory.getConnection()) {
            try {
                dao.create(user1);
            } catch (UserDaoException e) {
                e.printStackTrace();
            }
            connection.close();
        }

        try (Connection connection = ConnectionFactory.getConnection()) {
            Optional<User> user = Optional.empty();
            try {
                dao.update(1, "khan_updated", "1234");
            } catch (UserDaoException e) {
                e.printStackTrace();
            }
            user.ifPresent(user2 -> System.out.println(user2.getUsername()));
            connection.close();
        }
    }
}
 */
/*Scanner scanner = new Scanner(System.in);
 String password = scanner.nextLine();
        String username = scanner.nextLine();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO user (password, username) VALUES (?, ?)");
        statement.setString(1, password);
        statement.setString(2, username);*//*

        */
/*statement.execute();*/
