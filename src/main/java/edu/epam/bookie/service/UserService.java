package edu.epam.bookie.service;

import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.model.User;

import java.time.LocalDate;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserByUsernameAndPassword(String username, String password) throws UserServiceException;

    boolean checkUser(String username, String password) throws UserServiceException;

    boolean registerUser(String username, String first_name, String last_name, String email, String password, LocalDate date_of_birth) throws UserServiceException;
}
