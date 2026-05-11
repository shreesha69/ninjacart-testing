package com.krce.ninja.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "config.properties not found in classpath. " +
                                "Make sure it exists in src/test/resources/"
                );
            }
            props.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String getBrowser()       { return props.getProperty("browser"); }
    public static String getBaseUrl()       { return props.getProperty("base.url"); }
    public static int    getTimeout()       { return Integer.parseInt(props.getProperty("timeout", "10")); }
    public static String getValidEmail()    { return props.getProperty("valid.email"); }
    public static String getValidPassword() { return props.getProperty("valid.password"); }
}