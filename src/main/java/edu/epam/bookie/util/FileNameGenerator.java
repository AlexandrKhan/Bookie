package edu.epam.bookie.util;

import java.util.UUID;

/**
 * Generates random name for passport scan
 */
public class FileNameGenerator {
    public static String generateName(String fileName) {
        StringBuilder result = new StringBuilder();
        result.append(UUID.randomUUID());
        String file = fileName.substring(fileName.lastIndexOf("."));
        result.append(file);
        return result.toString();
    }
}
