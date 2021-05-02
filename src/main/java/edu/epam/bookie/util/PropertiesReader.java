package edu.epam.bookie.util;

import edu.epam.bookie.exception.PropertyReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reader for bundle properties (mail and DB)
 */
public class PropertiesReader {
    private static final Logger logger = LogManager.getLogger(PropertiesReader.class);

    private PropertiesReader() {
    }

    public static Properties readProperties(String path) throws PropertyReaderException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(path)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            logger.info("Properties were successfully read form '{}'", path);
            return properties;
        } catch (IOException e) {
            throw new PropertyReaderException("Impossible to read properties!");
        }
    }
}
