package com.mobiletest.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * ForgotPasswordPage handles password recovery functionality.
 */
public class ForgotPasswordPage extends BasePage {
    
    @AndroidFindBy(id = "com.example.app:id/forgot_password_title")
    private WebElement pageTitle;
    
    @AndroidFindBy(id = "com.example.app:id/email_input")
    private WebElement emailInput;
    
    @AndroidFindBy(id = "com.example.app:id/submit_button")
    private WebElement submitButton;
    
    @AndroidFindBy(id = "com.example.app:id/back_to_login_link")
    private WebElement backToLoginLink;
    
    @AndroidFindBy(id = "com.example.app:id/success_message")
    private WebElement successMessage;
    
    @AndroidFindBy(id = "com.example.app:id/error_message")
    private WebElement errorMessage;
    
    private final By pageTitleLocator = By.id("com.example.app:id/forgot_password_title");
    private final By successMessageLocator = By.id("com.example.app:id/success_message");
    private final By errorMessageLocator = By.id("com.example.app:id/error_message");
    
    public ForgotPasswordPage(AndroidDriver driver) {
        super(driver);
    }
    
    @Override
    public boolean isPageLoaded() {
        return isDisplayed(pageTitleLocator);
    }
    
    public ForgotPasswordPage enterEmail(String email) {
        enterText(emailInput, email);
        return this;
    }
    
    public ForgotPasswordPage clickSubmit() {
        click(submitButton);
        return this;
    }
    
    public ForgotPasswordPage requestPasswordReset(String email) {
        enterEmail(email);
        clickSubmit();
        return this;
    }
    
    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessageLocator);
    }
    
    public String getSuccessMessage() {
        return getText(successMessage);
    }
    
    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessageLocator);
    }
    
    public String getErrorMessage() {
        return getText(errorMessage);
    }
    
    public LoginPage navigateBackToLogin() {
        click(backToLoginLink);
        return new LoginPage(driver);
    }
}
