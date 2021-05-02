package edu.epam.bookie.util;

import edu.epam.bookie.util.PasswordEncryption;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PasswordEncryptionTest {
    @Test
    public void testEncryptPassword() {
        String pass = "password123";
        String encrypted = PasswordEncryption.encryptMessage(pass);
        assertNotEquals(pass, encrypted);
    }

    @Test
    public void testEncryptPasswordTrue() {
        String pass = "password123";
        String actual = PasswordEncryption.encryptMessage(pass);
        String expected = "AQnQ|Uqgzh7g~JKg";
        assertEquals(actual, expected);
    }
}
