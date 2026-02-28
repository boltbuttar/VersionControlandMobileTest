package com.mobiletest.tests;

import com.mobiletest.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * SignupTests contains test cases for user registration functionality.
 */
public class SignupTests extends BaseTest {
    
    private SignupPage signupPage;
    
    @BeforeMethod
    public void navigateToSignup() {
        signupPage = loginPage.clickSignup();
    }
    
    /**
     * Test Case 18: Verify signup page loads correctly
     */
    @Test(priority = 1, description = "Verify signup page loads with all required fields")
    public void testSignupPageLoads() {
        logInfo("Verifying signup page is loaded");
        
        Assert.assertTrue(signupPage.isPageLoaded(), "Signup page should be loaded");
        
        logPass("Signup page loaded successfully");
    }
    
    /**
     * Test Case 19: Verify successful user registration
     */
    @Test(priority = 2, description = "Verify user can register with valid details")
    public void testSuccessfulSignup() {
        logInfo("Performing user registration");
        
        String uniqueUsername = "testuser_" + System.currentTimeMillis();
        String email = uniqueUsername + "@test.com";
        
        signupPage.enterFullName("Test User")
                  .enterEmail(email)
                  .enterPhone("1234567890")
                  .enterUsername(uniqueUsername)
                  .enterPassword("Test@123")
                  .enterConfirmPassword("Test@123")
                  .acceptTerms()
                  .clickSignupButton();
        
        // Check for success or navigation to login
        Assert.assertTrue(signupPage.isSuccessDisplayed() || loginPage.isPageLoaded(),
                "Should show success message or redirect to login");
        
        logPass("User registration completed");
    }
    
    /**
     * Test Case 20: Verify signup fails with mismatched passwords
     */
    @Test(priority = 3, description = "Verify signup fails when passwords don't match")
    public void testSignupWithMismatchedPasswords() {
        logInfo("Testing signup with mismatched passwords");
        
        signupPage.enterFullName("Test User")
                  .enterEmail("test@test.com")
                  .enterUsername("testuser123")
                  .enterPassword("Test@123")
                  .enterConfirmPassword("DifferentPassword")
                  .acceptTerms()
                  .clickSignupButton();
        
        Assert.assertTrue(signupPage.isErrorDisplayed(), "Error should be displayed for mismatched passwords");
        
        logPass("Mismatched passwords handled correctly");
    }
    
    /**
     * Test Case 21: Verify signup fails without accepting terms
     */
    @Test(priority = 4, description = "Verify signup requires terms acceptance")
    public void testSignupWithoutTermsAcceptance() {
        logInfo("Testing signup without accepting terms");
        
        signupPage.enterFullName("Test User")
                  .enterEmail("test@test.com")
                  .enterUsername("testuser123")
                  .enterPassword("Test@123")
                  .enterConfirmPassword("Test@123")
                  .clickSignupButton();
        
        // Should show error or button should be disabled
        Assert.assertTrue(signupPage.isErrorDisplayed() || !signupPage.isSignupButtonEnabled(),
                "Should prevent signup without terms acceptance");
        
        logPass("Terms acceptance validation working correctly");
    }
}
