package edu.epam.bookie.service;

import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByUsernameAndPassword(String username, String password) throws UserServiceException;

    boolean checkUser(String username, String password) throws UserServiceException;

    boolean registerUser(String username, String password) throws UserServiceException;
}
