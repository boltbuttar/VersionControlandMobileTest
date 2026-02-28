package com.mobiletest.utils;

import com.mobiletest.config.AppiumConfig;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * WaitUtils class provides utility methods for various wait operations.
 * Implements explicit waits with customizable timeouts.
 */
public class WaitUtils {
    
    private final AndroidDriver driver;
    private final WebDriverWait wait;
    private final int defaultTimeout;
    
    /**
     * Constructor initializes WaitUtils with driver and default timeout
     * @param driver AndroidDriver instance
     */
    public WaitUtils(AndroidDriver driver) {
        this.driver = driver;
        this.defaultTimeout = AppiumConfig.getExplicitWait();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout));
    }
    
    /**
     * Wait for element to be visible
     * @param locator Element locator
     * @return WebElement once visible
     */
    public WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be visible with custom timeout
     * @param locator Element locator
     * @param timeoutSeconds Custom timeout in seconds
     * @return WebElement once visible
     */
    public WebElement waitForElementVisible(By locator, int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be clickable
     * @param locator Element locator
     * @return WebElement once clickable
     */
    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Wait for element to be clickable with custom timeout
     * @param locator Element locator
     * @param timeoutSeconds Custom timeout in seconds
     * @return WebElement once clickable
     */
    public WebElement waitForElementClickable(By locator, int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return customWait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Wait for element to be present in DOM
     * @param locator Element locator
     * @return WebElement once present
     */
    public WebElement waitForElementPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    /**
     * Wait for all elements to be visible
     * @param locator Element locator
     * @return List of WebElements once visible
     */
    public List<WebElement> waitForAllElementsVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    
    /**
     * Wait for element to become invisible
     * @param locator Element locator
     * @return true if element becomes invisible
     */
    public boolean waitForElementInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for element to contain specific text
     * @param locator Element locator
     * @param text Expected text
     * @return true if element contains text
     */
    public boolean waitForTextPresent(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
    
    /**
     * Wait for specific number of elements
     * @param locator Element locator
     * @param count Expected count
     * @return List of WebElements
     */
    public List<WebElement> waitForNumberOfElements(By locator, int count) {
        return wait.until(ExpectedConditions.numberOfElementsToBe(locator, count));
    }
    
    /**
     * Check if element is displayed with implicit wait disabled
     * @param locator Element locator
     * @param timeoutSeconds Timeout in seconds
     * @return true if element is displayed
     */
    public boolean isElementDisplayed(By locator, int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    
    /**
     * Wait for page to load completely
     * @param timeoutSeconds Timeout in seconds
     */
    public void waitForPageLoad(int timeoutSeconds) {
        try {
            Thread.sleep(timeoutSeconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
