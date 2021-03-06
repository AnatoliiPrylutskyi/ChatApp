package com.aprylutskyi.chat.server.configuration;

import org.apache.log4j.*;

import java.io.IOException;

public class LoggerConfigurer {

    private static final String pattern = "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n";

    private LoggerConfigurer() {
    }

    public static void configureConsoleAppender() {
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);

        ConsoleAppender consoleAppender = new ConsoleAppender(new PatternLayout(pattern));
        consoleAppender.setTarget("System.out");
        rootLogger.addAppender(consoleAppender);
    }

    public static void configureFileAppender(String filePath) {
        Logger rootLogger = Logger.getRootLogger();
        RollingFileAppender fileAppender = null;
        try {
            fileAppender = new RollingFileAppender(new PatternLayout(pattern), filePath);
            rootLogger.addAppender(fileAppender);
        } catch (IOException e) {
            System.err.println("Can't create log file, please change file path/name");
        }
    }


}
