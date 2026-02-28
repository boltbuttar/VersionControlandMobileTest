package com.mobiletest.tests;

import com.aventstack.extentreports.Status;
import com.mobiletest.config.AppiumConfig;
import com.mobiletest.utils.ExtentReportManager;
import com.mobiletest.utils.ScreenshotUtils;
import io.appium.java_client.android.AndroidDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestListener implements ITestListener for custom test event handling.
 * Integrates with ExtentReports for comprehensive test reporting.
 */
public class TestListener implements ITestListener {
    
    @Override
    public void onStart(ITestContext context) {
        System.out.println("========================================");
        System.out.println("Test Suite Started: " + context.getName());
        System.out.println("========================================");
    }
    
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("========================================");
        System.out.println("Test Suite Finished: " + context.getName());
        System.out.println("Passed: " + context.getPassedTests().size());
        System.out.println("Failed: " + context.getFailedTests().size());
        System.out.println("Skipped: " + context.getSkippedTests().size());
        System.out.println("========================================");
        
        // Flush extent reports
        ExtentReportManager.flushReports();
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("Starting test: " + testName);
        ExtentReportManager.createTest(testName, result.getMethod().getDescription());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("Test PASSED: " + testName);
        
        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().log(Status.PASS, "Test passed successfully");
        }
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("Test FAILED: " + testName);
        
        // Capture screenshot on failure
        try {
            AndroidDriver driver = AppiumConfig.getDriver();
            String screenshotPath = ScreenshotUtils.captureFailureScreenshot(driver, testName);
            
            if (ExtentReportManager.getTest() != null) {
                ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + result.getThrowable().getMessage());
                if (screenshotPath != null) {
                    ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("Test SKIPPED: " + testName);
        
        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().log(Status.SKIP, "Test skipped");
        }
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not used
    }
}
