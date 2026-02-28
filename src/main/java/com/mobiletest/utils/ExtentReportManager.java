package com.mobiletest.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ExtentReportManager manages the Extent Reports for test reporting.
 * Generates HTML reports with detailed test execution information.
 */
public class ExtentReportManager {
    
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final String REPORT_PATH = "test-output/reports/";
    
    /**
     * Private constructor to prevent instantiation
     */
    private ExtentReportManager() {
    }
    
    /**
     * Initialize and return ExtentReports instance
     * @return ExtentReports instance
     */
    public static ExtentReports getReporter() {
        if (extent == null) {
            initializeReporter();
        }
        return extent;
    }
    
    /**
     * Initialize the Extent Reports with configuration
     */
    private static void initializeReporter() {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportPath = REPORT_PATH + "TestReport_" + timestamp + ".html";
        
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        
        // Configure report appearance
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Mobile Test Automation Report");
        sparkReporter.config().setReportName("Appium Test Results");
        sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        // Add system information
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Framework", "Appium + TestNG");
        extent.setSystemInfo("Platform", "Android");
    }
    
    /**
     * Create a new test in the report
     * @param testName Name of the test
     * @return ExtentTest instance
     */
    public static ExtentTest createTest(String testName) {
        ExtentTest extentTest = getReporter().createTest(testName);
        test.set(extentTest);
        return extentTest;
    }
    
    /**
     * Create a new test with description
     * @param testName Name of the test
     * @param description Test description
     * @return ExtentTest instance
     */
    public static ExtentTest createTest(String testName, String description) {
        ExtentTest extentTest = getReporter().createTest(testName, description);
        test.set(extentTest);
        return extentTest;
    }
    
    /**
     * Get current test instance
     * @return Current ExtentTest instance
     */
    public static ExtentTest getTest() {
        return test.get();
    }
    
    /**
     * Flush the reports to file
     */
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
    
    /**
     * Remove test from ThreadLocal
     */
    public static void removeTest() {
        test.remove();
    }
}
