package com.mobiletest.utils;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtils provides utility methods for capturing screenshots.
 * Used for debugging and test failure documentation.
 */
public class ScreenshotUtils {
    
    private static final String SCREENSHOT_PATH = "test-output/screenshots/";
    
    /**
     * Capture screenshot and save to file
     * @param driver AndroidDriver instance
     * @param screenshotName Name for the screenshot
     * @return Path to the saved screenshot
     */
    public static String captureScreenshot(AndroidDriver driver, String screenshotName) {
        // Create timestamp for unique filename
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String fileName = screenshotName + "_" + timestamp + ".png";
        String filePath = SCREENSHOT_PATH + fileName;
        
        // Ensure directory exists
        File directory = new File(SCREENSHOT_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        try {
            // Capture screenshot
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(filePath);
            FileUtils.copyFile(sourceFile, destinationFile);
            
            System.out.println("Screenshot saved: " + filePath);
            return filePath;
            
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Capture screenshot and return as Base64 string
     * @param driver AndroidDriver instance
     * @return Base64 encoded screenshot string
     */
    public static String captureScreenshotAsBase64(AndroidDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
    
    /**
     * Capture screenshot for test failure
     * @param driver AndroidDriver instance
     * @param testName Name of the failed test
     * @return Path to the saved screenshot
     */
    public static String captureFailureScreenshot(AndroidDriver driver, String testName) {
        return captureScreenshot(driver, "FAILURE_" + testName);
    }
}
