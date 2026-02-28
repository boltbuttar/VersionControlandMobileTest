package com.mobiletest.config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

/**
 * AppiumConfig class manages the Appium driver configuration and initialization.
 * This class follows the Singleton pattern to ensure only one driver instance exists.
 */
public class AppiumConfig {
    
    private static AndroidDriver driver;
    private static Properties properties;
    private static final String CONFIG_FILE = "src/test/resources/config.properties";
    
    /**
     * Private constructor to prevent instantiation
     */
    private AppiumConfig() {
    }
    
    /**
     * Load configuration properties from file
     */
    public static Properties loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
                properties.load(fis);
            } catch (IOException e) {
                System.err.println("Config file not found. Using default values.");
                setDefaultProperties();
            }
        }
        return properties;
    }
    
    /**
     * Set default properties if config file is not found
     */
    private static void setDefaultProperties() {
        properties = new Properties();
        properties.setProperty("appium.server.url", "http://127.0.0.1:4723");
        properties.setProperty("platform.name", "Android");
        properties.setProperty("device.name", "Android Emulator");
        properties.setProperty("automation.name", "UiAutomator2");
        properties.setProperty("app.package", "com.example.app");
        properties.setProperty("app.activity", "com.example.app.MainActivity");
        properties.setProperty("implicit.wait", "10");
        properties.setProperty("explicit.wait", "20");
    }
    
    /**
     * Initialize and return the AndroidDriver instance
     * @return AndroidDriver instance
     */
    public static AndroidDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }
    
    /**
     * Initialize the Appium driver with configured capabilities
     */
    private static void initializeDriver() {
        loadProperties();
        
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(properties.getProperty("platform.name", "Android"));
        options.setDeviceName(properties.getProperty("device.name", "Android Emulator"));
        options.setAutomationName(properties.getProperty("automation.name", "UiAutomator2"));
        options.setAppPackage(properties.getProperty("app.package"));
        options.setAppActivity(properties.getProperty("app.activity"));
        options.setNoReset(Boolean.parseBoolean(properties.getProperty("no.reset", "true")));
        
        // Set app path if provided
        String appPath = properties.getProperty("app.path");
        if (appPath != null && !appPath.isEmpty()) {
            options.setApp(appPath);
        }
        
        try {
            String serverUrl = properties.getProperty("appium.server.url", "http://127.0.0.1:4723");
            driver = new AndroidDriver(new URL(serverUrl), options);
            
            // Set implicit wait
            int implicitWait = Integer.parseInt(properties.getProperty("implicit.wait", "10"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL: " + e.getMessage());
        }
    }
    
    /**
     * Quit the driver and clean up resources
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    
    /**
     * Get property value by key
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        loadProperties();
        return properties.getProperty(key);
    }
    
    /**
     * Get explicit wait timeout
     * @return Explicit wait timeout in seconds
     */
    public static int getExplicitWait() {
        loadProperties();
        return Integer.parseInt(properties.getProperty("explicit.wait", "20"));
    }
}
