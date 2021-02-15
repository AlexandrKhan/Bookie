package edu.epam.bookie.util;

import java.util.Base64;

public class PasswordEncryption {
    private static final int OFFSET = 4;

    private PasswordEncryption() {
    }

    public static String encryptMessage(String text) {
        String b64encoded =
                Base64.getEncoder().encodeToString(text.getBytes());
        String reverse = new StringBuffer(b64encoded).reverse().toString();
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < reverse.length(); i++) {
            tmp.append((char) (reverse.charAt(i) + OFFSET));
        }
        return tmp.toString();
    }
}
