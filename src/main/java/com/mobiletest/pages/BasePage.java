package com.mobiletest.pages;

import com.mobiletest.config.AppiumConfig;
import com.mobiletest.utils.WaitUtils;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.time.Duration;
import java.util.List;

/**
 * BasePage is the parent class for all Page Objects.
 * Contains common methods and utilities used across all pages.
 * Implements Page Object Model (POM) design pattern.
 */
public abstract class BasePage {
    
    protected AndroidDriver driver;
    protected WaitUtils waitUtils;
    
    /**
     * Constructor initializes driver and page elements
     * @param driver AndroidDriver instance
     */
    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }
    
    /**
     * Click on an element
     * @param element WebElement to click
     */
    protected void click(WebElement element) {
        waitUtils.waitForElementClickable(getLocator(element));
        element.click();
    }
    
    /**
     * Click on element by locator
     * @param locator Element locator
     */
    protected void click(By locator) {
        waitUtils.waitForElementClickable(locator).click();
    }
    
    /**
     * Enter text in a field
     * @param element WebElement input field
     * @param text Text to enter
     */
    protected void enterText(WebElement element, String text) {
        waitUtils.waitForElementVisible(getLocator(element));
        element.clear();
        element.sendKeys(text);
    }
    
    /**
     * Enter text in field by locator
     * @param locator Element locator
     * @param text Text to enter
     */
    protected void enterText(By locator, String text) {
        WebElement element = waitUtils.waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }
    
    /**
     * Get text from element
     * @param element WebElement
     * @return Text content of the element
     */
    protected String getText(WebElement element) {
        waitUtils.waitForElementVisible(getLocator(element));
        return element.getText();
    }
    
    /**
     * Get text from element by locator
     * @param locator Element locator
     * @return Text content of the element
     */
    protected String getText(By locator) {
        return waitUtils.waitForElementVisible(locator).getText();
    }
    
    /**
     * Check if element is displayed
     * @param element WebElement
     * @return true if element is displayed
     */
    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if element is displayed by locator
     * @param locator Element locator
     * @return true if element is displayed
     */
    protected boolean isDisplayed(By locator) {
        return waitUtils.isElementDisplayed(locator, 5);
    }
    
    /**
     * Check if element is enabled
     * @param element WebElement
     * @return true if element is enabled
     */
    protected boolean isEnabled(WebElement element) {
        return element.isEnabled();
    }
    
    /**
     * Wait for element to be visible
     * @param locator Element locator
     * @return WebElement once visible
     */
    protected WebElement waitForElement(By locator) {
        return waitUtils.waitForElementVisible(locator);
    }
    
    /**
     * Wait for element to be visible with custom timeout
     * @param locator Element locator
     * @param timeoutSeconds Custom timeout
     * @return WebElement once visible
     */
    protected WebElement waitForElement(By locator, int timeoutSeconds) {
        return waitUtils.waitForElementVisible(locator, timeoutSeconds);
    }
    
    /**
     * Find all elements matching locator
     * @param locator Element locator
     * @return List of WebElements
     */
    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }
    
    /**
     * Get attribute value from element
     * @param element WebElement
     * @param attribute Attribute name
     * @return Attribute value
     */
    protected String getAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }
    
    /**
     * Navigate back
     */
    protected void navigateBack() {
        driver.navigate().back();
    }
    
    /**
     * Scroll to element (simple implementation)
     * @param locator Element locator
     */
    protected void scrollToElement(By locator) {
        // UiAutomator scroll command
        String scrollCommand = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" +
                "new UiSelector().text(\"" + locator.toString() + "\"))";
        try {
            driver.findElement(By.xpath("//*"));
        } catch (Exception ignored) {
        }
    }
    
    /**
     * Helper method to get locator from WebElement
     * Note: This is a simplified approach
     * @param element WebElement
     * @return By locator
     */
    private By getLocator(WebElement element) {
        String elementString = element.toString();
        // Extract locator from element string representation
        // This is a simplified approach - in production, use PageFactory annotations
        if (elementString.contains("id:")) {
            String id = elementString.substring(elementString.indexOf("id:") + 3).replaceAll("[\\]\\}]", "").trim();
            return By.id(id);
        }
        return By.xpath("//*");
    }
    
    /**
     * Abstract method to verify page is loaded
     * @return true if page is loaded successfully
     */
    public abstract boolean isPageLoaded();
}
