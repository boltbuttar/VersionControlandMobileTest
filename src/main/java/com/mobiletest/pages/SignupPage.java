package com.mobiletest.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * SignupPage represents the user registration screen.
 * Contains all elements and actions for creating a new account.
 */
public class SignupPage extends BasePage {
    
    // Element Locators
    @AndroidFindBy(id = "com.example.app:id/signup_title")
    private WebElement signupTitle;
    
    @AndroidFindBy(id = "com.example.app:id/fullname_input")
    private WebElement fullNameInput;
    
    @AndroidFindBy(id = "com.example.app:id/email_input")
    private WebElement emailInput;
    
    @AndroidFindBy(id = "com.example.app:id/phone_input")
    private WebElement phoneInput;
    
    @AndroidFindBy(id = "com.example.app:id/username_input")
    private WebElement usernameInput;
    
    @AndroidFindBy(id = "com.example.app:id/password_input")
    private WebElement passwordInput;
    
    @AndroidFindBy(id = "com.example.app:id/confirm_password_input")
    private WebElement confirmPasswordInput;
    
    @AndroidFindBy(id = "com.example.app:id/terms_checkbox")
    private WebElement termsCheckbox;
    
    @AndroidFindBy(id = "com.example.app:id/signup_button")
    private WebElement signupButton;
    
    @AndroidFindBy(id = "com.example.app:id/login_link")
    private WebElement loginLink;
    
    @AndroidFindBy(id = "com.example.app:id/error_message")
    private WebElement errorMessage;
    
    @AndroidFindBy(id = "com.example.app:id/success_message")
    private WebElement successMessage;
    
    // By locators
    private final By signupTitleLocator = By.id("com.example.app:id/signup_title");
    private final By errorMessageLocator = By.id("com.example.app:id/error_message");
    private final By successMessageLocator = By.id("com.example.app:id/success_message");
    
    /**
     * Constructor
     * @param driver AndroidDriver instance
     */
    public SignupPage(AndroidDriver driver) {
        super(driver);
    }
    
    /**
     * Verify if signup page is loaded
     * @return true if signup page is displayed
     */
    @Override
    public boolean isPageLoaded() {
        return isDisplayed(signupTitleLocator);
    }
    
    /**
     * Enter full name
     * @param fullName Full name
     * @return SignupPage instance for chaining
     */
    public SignupPage enterFullName(String fullName) {
        enterText(fullNameInput, fullName);
        return this;
    }
    
    /**
     * Enter email
     * @param email Email address
     * @return SignupPage instance for chaining
     */
    public SignupPage enterEmail(String email) {
        enterText(emailInput, email);
        return this;
    }
    
    /**
     * Enter phone number
     * @param phone Phone number
     * @return SignupPage instance for chaining
     */
    public SignupPage enterPhone(String phone) {
        enterText(phoneInput, phone);
        return this;
    }
    
    /**
     * Enter username
     * @param username Username
     * @return SignupPage instance for chaining
     */
    public SignupPage enterUsername(String username) {
        enterText(usernameInput, username);
        return this;
    }
    
    /**
     * Enter password
     * @param password Password
     * @return SignupPage instance for chaining
     */
    public SignupPage enterPassword(String password) {
        enterText(passwordInput, password);
        return this;
    }
    
    /**
     * Enter confirm password
     * @param confirmPassword Confirm password
     * @return SignupPage instance for chaining
     */
    public SignupPage enterConfirmPassword(String confirmPassword) {
        enterText(confirmPasswordInput, confirmPassword);
        return this;
    }
    
    /**
     * Accept terms and conditions
     * @return SignupPage instance for chaining
     */
    public SignupPage acceptTerms() {
        click(termsCheckbox);
        return this;
    }
    
    /**
     * Click signup button
     * @return SignupPage instance
     */
    public SignupPage clickSignupButton() {
        click(signupButton);
        return this;
    }
    
    /**
     * Perform complete signup
     * @param fullName Full name
     * @param email Email
     * @param phone Phone number
     * @param username Username
     * @param password Password
     * @return LoginPage instance after successful signup
     */
    public LoginPage signup(String fullName, String email, String phone, 
                            String username, String password) {
        enterFullName(fullName);
        enterEmail(email);
        enterPhone(phone);
        enterUsername(username);
        enterPassword(password);
        enterConfirmPassword(password);
        acceptTerms();
        click(signupButton);
        return new LoginPage(driver);
    }
    
    /**
     * Check if error message is displayed
     * @return true if error is visible
     */
    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessageLocator);
    }
    
    /**
     * Get error message text
     * @return Error message
     */
    public String getErrorMessage() {
        return getText(errorMessage);
    }
    
    /**
     * Check if success message is displayed
     * @return true if success message is visible
     */
    public boolean isSuccessDisplayed() {
        return isDisplayed(successMessageLocator);
    }
    
    /**
     * Navigate back to login
     * @return LoginPage instance
     */
    public LoginPage navigateToLogin() {
        click(loginLink);
        return new LoginPage(driver);
    }
    
    /**
     * Check if signup button is enabled
     * @return true if button is enabled
     */
    public boolean isSignupButtonEnabled() {
        return isEnabled(signupButton);
    }
}
