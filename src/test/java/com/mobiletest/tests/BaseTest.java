package com.mobiletest.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.mobiletest.config.AppiumConfig;
import com.mobiletest.pages.LoginPage;
import com.mobiletest.utils.ExtentReportManager;
import com.mobiletest.utils.ScreenshotUtils;
import io.appium.java_client.android.AndroidDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * BaseTest is the parent class for all test classes.
 * Handles test setup, teardown, and reporting.
 */
public class BaseTest {
    
    protected AndroidDriver driver;
    protected LoginPage loginPage;
    protected ExtentTest extentTest;
    
    // Test data
    protected static final String VALID_USERNAME = "testuser";
    protected static final String VALID_PASSWORD = "Test@123";
    protected static final String INVALID_USERNAME = "invaliduser";
    protected static final String INVALID_PASSWORD = "wrongpassword";
    
    /**
     * Setup method runs before each test class
     */
    @BeforeClass
    public void classSetUp() {
        // Initialize driver once per class
        driver = AppiumConfig.getDriver();
    }
    
    /**
     * Setup method runs before each test method
     * @param testMethod Test method being executed
     */
    @BeforeMethod
    public void setUp(java.lang.reflect.Method testMethod) {
        // Create extent test for reporting
        String testName = testMethod.getName();
        String description = testMethod.getAnnotation(org.testng.annotations.Test.class).description();
        extentTest = ExtentReportManager.createTest(testName, description);
        
        // Initialize login page (app starting point)
        loginPage = new LoginPage(driver);
        
        logInfo("Starting test: " + testName);
    }
    
    /**
     * Teardown method runs after each test method
     * @param result Test result
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        
        if (result.getStatus() == ITestResult.SUCCESS) {
            logPass("Test passed: " + testName);
        } else if (result.getStatus() == ITestResult.FAILURE) {
            logFail("Test failed: " + testName);
            // Capture screenshot on failure
            String screenshotPath = ScreenshotUtils.captureFailureScreenshot(driver, testName);
            if (screenshotPath != null) {
                extentTest.addScreenCaptureFromPath(screenshotPath);
            }
            // Log exception
            if (result.getThrowable() != null) {
                extentTest.log(Status.FAIL, result.getThrowable());
            }
        } else if (result.getStatus() == ITestResult.SKIP) {
            logWarning("Test skipped: " + testName);
        }
        
        ExtentReportManager.removeTest();
    }
    
    /**
     * Teardown method runs after each test class
     */
    @AfterClass
    public void classTearDown() {
        // Don't quit driver here to maintain session across test classes
    }
    
    /**
     * Suite teardown - runs after all tests
     */
    @AfterSuite
    public void suiteTearDown() {
        // Flush reports
        ExtentReportManager.flushReports();
        
        // Quit driver
        AppiumConfig.quitDriver();
    }
    
    /**
     * Log info message to report
     * @param message Info message
     */
    protected void logInfo(String message) {
        System.out.println("[INFO] " + message);
        if (extentTest != null) {
            extentTest.log(Status.INFO, message);
        }
    }
    
    /**
     * Log pass message to report
     * @param message Pass message
     */
    protected void logPass(String message) {
        System.out.println("[PASS] " + message);
        if (extentTest != null) {
            extentTest.log(Status.PASS, message);
        }
    }
    
    /**
     * Log fail message to report
     * @param message Fail message
     */
    protected void logFail(String message) {
        System.out.println("[FAIL] " + message);
        if (extentTest != null) {
            extentTest.log(Status.FAIL, message);
        }
    }
    
    /**
     * Log warning message to report
     * @param message Warning message
     */
    protected void logWarning(String message) {
        System.out.println("[WARN] " + message);
        if (extentTest != null) {
            extentTest.log(Status.WARNING, message);
        }
    }
    
    /**
     * Capture screenshot and attach to report
     * @param screenshotName Name for the screenshot
     */
    protected void captureScreenshot(String screenshotName) {
        String screenshotPath = ScreenshotUtils.captureScreenshot(driver, screenshotName);
        if (screenshotPath != null && extentTest != null) {
            try {
                extentTest.addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                logWarning("Failed to attach screenshot: " + e.getMessage());
            }
        }
    }
}
