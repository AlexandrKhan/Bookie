package edu.epam.bookie.service;

import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.model.User;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll() throws UserServiceException;

    boolean checkUser(String username, String password) throws UserServiceException;

    User registerUser(String username, String firstName, String lastName, String email, String password, String repeatPassword, LocalDate dateOfBirth, String scan) throws UserServiceException;

    Optional<User> findUserByUsernameAndPassword(String username, String password) throws UserServiceException;

    boolean activateAccount(String userId) throws UserServiceException;

    Optional<String> findEmailById(String id) throws UserServiceException;

    boolean blockUser(int username) throws UserServiceException;

    boolean unblockUser(int username) throws UserServiceException;


}
