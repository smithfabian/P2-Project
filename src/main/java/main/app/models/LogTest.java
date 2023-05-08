package main.app.models;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogTest {
    private static final Logger logger = LogManager.getLogger(LogTest.class.getName());
    public static void main(String[] args) {
        logger.debug("Bug message");
    }
}

