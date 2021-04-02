package edu.epam.bookie.model.builder;

import edu.epam.bookie.model.User;

public class UserBuilder {
    private User user;

    private UserBuilder() {
        user = new User();
    }

    public static User createUser() {
        return new User();
    }

    public UserBuilder withId(int id) {
        user.setId(id);
        return this;
    }



}
