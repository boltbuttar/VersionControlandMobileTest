package com.mobiletest.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * LoginPage represents the Login screen of the application.
 * Contains all elements and actions related to user login.
 */
public class LoginPage extends BasePage {
    
    // Element Locators using @AndroidFindBy annotation
    @AndroidFindBy(id = "com.example.app:id/username_input")
    private WebElement usernameInput;
    
    @AndroidFindBy(id = "com.example.app:id/password_input")
    private WebElement passwordInput;
    
    @AndroidFindBy(id = "com.example.app:id/login_button")
    private WebElement loginButton;
    
    @AndroidFindBy(id = "com.example.app:id/forgot_password_link")
    private WebElement forgotPasswordLink;
    
    @AndroidFindBy(id = "com.example.app:id/signup_link")
    private WebElement signupLink;
    
    @AndroidFindBy(id = "com.example.app:id/error_message")
    private WebElement errorMessage;
    
    @AndroidFindBy(id = "com.example.app:id/remember_me_checkbox")
    private WebElement rememberMeCheckbox;
    
    @AndroidFindBy(id = "com.example.app:id/login_logo")
    private WebElement loginLogo;
    
    // Alternative locators using By
    private final By usernameInputLocator = By.id("com.example.app:id/username_input");
    private final By passwordInputLocator = By.id("com.example.app:id/password_input");
    private final By loginButtonLocator = By.id("com.example.app:id/login_button");
    private final By errorMessageLocator = By.id("com.example.app:id/error_message");
    
    /**
     * Constructor
     * @param driver AndroidDriver instance
     */
    public LoginPage(AndroidDriver driver) {
        super(driver);
    }
    
    /**
     * Verify if login page is loaded
     * @return true if login page is displayed
     */
    @Override
    public boolean isPageLoaded() {
        return isDisplayed(usernameInputLocator) && isDisplayed(loginButtonLocator);
    }
    
    /**
     * Enter username
     * @param username Username to enter
     * @return LoginPage instance for chaining
     */
    public LoginPage enterUsername(String username) {
        enterText(usernameInput, username);
        return this;
    }
    
    /**
     * Enter password
     * @param password Password to enter
     * @return LoginPage instance for chaining
     */
    public LoginPage enterPassword(String password) {
        enterText(passwordInput, password);
        return this;
    }
    
    /**
     * Click login button
     * @return HomePage instance if login successful
     */
    public HomePage clickLoginButton() {
        click(loginButton);
        return new HomePage(driver);
    }
    
    /**
     * Perform complete login action
     * @param username Username
     * @param password Password
     * @return HomePage instance if login successful
     */
    public HomePage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLoginButton();
    }
    
    /**
     * Perform login and expect failure
     * @param username Username
     * @param password Password
     * @return LoginPage instance (stays on login page)
     */
    public LoginPage loginExpectingFailure(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        click(loginButton);
        return this;
    }
    
    /**
     * Check if error message is displayed
     * @return true if error message is visible
     */
    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessageLocator);
    }
    
    /**
     * Get error message text
     * @return Error message text
     */
    public String getErrorMessageText() {
        return getText(errorMessage);
    }
    
    /**
     * Click on forgot password link
     * @return ForgotPasswordPage instance
     */
    public ForgotPasswordPage clickForgotPassword() {
        click(forgotPasswordLink);
        return new ForgotPasswordPage(driver);
    }
    
    /**
     * Click on signup link
     * @return SignupPage instance
     */
    public SignupPage clickSignup() {
        click(signupLink);
        return new SignupPage(driver);
    }
    
    /**
     * Toggle remember me checkbox
     * @return LoginPage instance for chaining
     */
    public LoginPage toggleRememberMe() {
        click(rememberMeCheckbox);
        return this;
    }
    
    /**
     * Check if remember me is checked
     * @return true if checkbox is checked
     */
    public boolean isRememberMeChecked() {
        String checked = getAttribute(rememberMeCheckbox, "checked");
        return "true".equals(checked);
    }
    
    /**
     * Check if login button is enabled
     * @return true if login button is enabled
     */
    public boolean isLoginButtonEnabled() {
        return isEnabled(loginButton);
    }
    
    /**
     * Clear all input fields
     * @return LoginPage instance for chaining
     */
    public LoginPage clearFields() {
        usernameInput.clear();
        passwordInput.clear();
        return this;
    }
    
    /**
     * Check if logo is displayed
     * @return true if logo is visible
     */
    public boolean isLogoDisplayed() {
        return isDisplayed(loginLogo);
    }
}
