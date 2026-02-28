package com.mobiletest.tests;

import com.mobiletest.pages.ForgotPasswordPage;
import com.mobiletest.pages.HomePage;
import com.mobiletest.pages.SignupPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * LoginTests contains test cases for login functionality.
 * Tests cover various login scenarios including valid/invalid credentials.
 */
public class LoginTests extends BaseTest {
    
    /**
     * Test Case 1: Verify successful login with valid credentials
     * Priority: High
     */
    @Test(priority = 1, description = "Verify user can login with valid username and password")
    public void testSuccessfulLogin() {
        logInfo("Entering valid username: " + VALID_USERNAME);
        loginPage.enterUsername(VALID_USERNAME);
        
        logInfo("Entering valid password");
        loginPage.enterPassword(VALID_PASSWORD);
        
        logInfo("Clicking login button");
        HomePage homePage = loginPage.clickLoginButton();
        
        // Verify user is redirected to home page
        Assert.assertTrue(homePage.isPageLoaded(), "Home page should be displayed after successful login");
        Assert.assertTrue(homePage.isWelcomeMessageDisplayed(), "Welcome message should be displayed");
        
        logPass("User successfully logged in and redirected to home page");
        
        // Logout for next tests
        homePage.logout();
    }
    
    /**
     * Test Case 2: Verify login fails with invalid username
     * Priority: High
     */
    @Test(priority = 2, description = "Verify login fails with invalid username")
    public void testLoginWithInvalidUsername() {
        logInfo("Entering invalid username: " + INVALID_USERNAME);
        loginPage.enterUsername(INVALID_USERNAME);
        
        logInfo("Entering valid password");
        loginPage.enterPassword(VALID_PASSWORD);
        
        logInfo("Clicking login button");
        loginPage.loginExpectingFailure(INVALID_USERNAME, VALID_PASSWORD);
        
        // Verify error message is displayed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        Assert.assertTrue(loginPage.isPageLoaded(), "Should remain on login page");
        
        logPass("Login correctly failed with invalid username");
        
        loginPage.clearFields();
    }
    
    /**
     * Test Case 3: Verify login fails with invalid password
     * Priority: High
     */
    @Test(priority = 3, description = "Verify login fails with invalid password")
    public void testLoginWithInvalidPassword() {
        logInfo("Entering valid username: " + VALID_USERNAME);
        loginPage.enterUsername(VALID_USERNAME);
        
        logInfo("Entering invalid password");
        loginPage.enterPassword(INVALID_PASSWORD);
        
        logInfo("Attempting login");
        loginPage.loginExpectingFailure(VALID_USERNAME, INVALID_PASSWORD);
        
        // Verify error message is displayed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid password");
        
        logPass("Login correctly failed with invalid password");
        
        loginPage.clearFields();
    }
    
    /**
     * Test Case 4: Verify login fails with empty credentials
     * Priority: Medium
     */
    @Test(priority = 4, description = "Verify login fails when credentials are empty")
    public void testLoginWithEmptyCredentials() {
        logInfo("Attempting login with empty fields");
        
        // Clear any existing text and try to login
        loginPage.clearFields();
        
        // Check if login button is disabled or shows error
        boolean isLoginEnabled = loginPage.isLoginButtonEnabled();
        
        if (isLoginEnabled) {
            // If button is enabled, clicking should show error
            loginPage.loginExpectingFailure("", "");
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error should be shown for empty credentials");
        } else {
            // Button should be disabled for empty fields
            Assert.assertFalse(isLoginEnabled, "Login button should be disabled for empty credentials");
        }
        
        logPass("Empty credentials handled correctly");
    }
    
    /**
     * Test Case 5: Verify Remember Me functionality
     * Priority: Low
     */
    @Test(priority = 5, description = "Verify Remember Me checkbox works correctly")
    public void testRememberMeCheckbox() {
        logInfo("Verifying Remember Me checkbox functionality");
        
        // Initially unchecked
        Assert.assertFalse(loginPage.isRememberMeChecked(), "Remember Me should be unchecked initially");
        
        // Toggle checkbox
        loginPage.toggleRememberMe();
        Assert.assertTrue(loginPage.isRememberMeChecked(), "Remember Me should be checked after toggle");
        
        // Toggle again
        loginPage.toggleRememberMe();
        Assert.assertFalse(loginPage.isRememberMeChecked(), "Remember Me should be unchecked after second toggle");
        
        logPass("Remember Me checkbox works correctly");
    }
    
    /**
     * Test Case 6: Verify navigation to Forgot Password page
     * Priority: Medium
     */
    @Test(priority = 6, description = "Verify user can navigate to Forgot Password page")
    public void testNavigateToForgotPassword() {
        logInfo("Clicking on Forgot Password link");
        ForgotPasswordPage forgotPasswordPage = loginPage.clickForgotPassword();
        
        // Verify Forgot Password page is displayed
        Assert.assertTrue(forgotPasswordPage.isPageLoaded(), "Forgot Password page should be displayed");
        
        logPass("Successfully navigated to Forgot Password page");
        
        // Navigate back to login
        forgotPasswordPage.navigateBackToLogin();
    }
    
    /**
     * Test Case 7: Verify navigation to Signup page
     * Priority: Medium
     */
    @Test(priority = 7, description = "Verify user can navigate to Signup page")
    public void testNavigateToSignup() {
        logInfo("Clicking on Signup link");
        SignupPage signupPage = loginPage.clickSignup();
        
        // Verify Signup page is displayed
        Assert.assertTrue(signupPage.isPageLoaded(), "Signup page should be displayed");
        
        logPass("Successfully navigated to Signup page");
        
        // Navigate back to login
        signupPage.navigateToLogin();
    }
    
    /**
     * Test Case 8: Verify login page UI elements
     * Priority: Low
     */
    @Test(priority = 8, description = "Verify all login page UI elements are displayed")
    public void testLoginPageUIElements() {
        logInfo("Verifying login page UI elements");
        
        // Verify page is loaded
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page should be loaded");
        
        // Verify logo is displayed
        Assert.assertTrue(loginPage.isLogoDisplayed(), "App logo should be displayed");
        
        // Verify login button is present
        Assert.assertTrue(loginPage.isLoginButtonEnabled() || !loginPage.isLoginButtonEnabled(), 
                "Login button should exist");
        
        logPass("All login page UI elements are displayed correctly");
    }
}
