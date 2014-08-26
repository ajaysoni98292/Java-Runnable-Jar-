package com.ajay.runnable.jar;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String args[]) {
        System.out.println("========this is runnable jar project ============");

        try {

            LoggerContext context = (LoggerContext)
                    LoggerFactory.getILoggerFactory();

            if (args.length > 0) {

                final String log4jFilePath = args[0];
                configureLogger(context, log4jFilePath);

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void configureLogger(final LoggerContext context, final String log4jFilePath) throws Exception {
        final File log4jFile = new File(log4jFilePath);

        if (!log4jFile.exists()) {
            throw new Exception("** " + log4jFilePath + " is not a valid path");
        }
        try {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            context.reset();

            configurator.doConfigure(log4jFile);

        } catch (JoranException e) {
            throw new Exception(e);
        }
    }
}
