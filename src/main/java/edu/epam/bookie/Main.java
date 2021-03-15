package edu.epam.bookie;

import com.google.gson.Gson;
import edu.epam.bookie.model.User;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        user.setFirstName("Alex");
        user.setLastName("Khan");

        Gson gson = new Gson();

        String obj = gson.toJson(user);
        System.out.println(obj);
    }
}
