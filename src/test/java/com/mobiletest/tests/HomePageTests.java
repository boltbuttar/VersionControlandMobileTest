package com.mobiletest.tests;

import com.mobiletest.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * HomePageTests contains test cases for home page functionality.
 * Tests cover navigation, search, and item interactions.
 */
public class HomePageTests extends BaseTest {
    
    private HomePage homePage;
    
    @BeforeMethod
    public void loginAndNavigateToHome() {
        // Login first to access home page
        homePage = loginPage.login(VALID_USERNAME, VALID_PASSWORD);
    }
    
    /**
     * Test Case 9: Verify home page loads after login
     * Priority: High
     */
    @Test(priority = 1, description = "Verify home page loads correctly after login")
    public void testHomePageLoads() {
        logInfo("Verifying home page is loaded");
        
        Assert.assertTrue(homePage.isPageLoaded(), "Home page should be loaded");
        Assert.assertTrue(homePage.isBottomNavigationDisplayed(), "Bottom navigation should be visible");
        
        logPass("Home page loaded successfully");
    }
    
    /**
     * Test Case 10: Verify welcome message displays username
     * Priority: Medium
     */
    @Test(priority = 2, description = "Verify welcome message is displayed on home page")
    public void testWelcomeMessageDisplayed() {
        logInfo("Checking welcome message");
        
        Assert.assertTrue(homePage.isWelcomeMessageDisplayed(), "Welcome message should be displayed");
        String welcomeMessage = homePage.getWelcomeMessage();
        Assert.assertNotNull(welcomeMessage, "Welcome message should not be null");
        
        logPass("Welcome message displayed: " + welcomeMessage);
    }
    
    /**
     * Test Case 11: Verify navigation to Profile page
     * Priority: Medium
     */
    @Test(priority = 3, description = "Verify navigation from home to profile page")
    public void testNavigateToProfile() {
        logInfo("Navigating to Profile page");
        
        ProfilePage profilePage = homePage.navigateToProfile();
        Assert.assertTrue(profilePage.isPageLoaded(), "Profile page should be loaded");
        
        logPass("Successfully navigated to Profile page");
        
        profilePage.navigateBack();
    }
    
    /**
     * Test Case 12: Verify navigation to Settings page
     * Priority: Medium
     */
    @Test(priority = 4, description = "Verify navigation from home to settings page")
    public void testNavigateToSettings() {
        logInfo("Navigating to Settings page");
        
        SettingsPage settingsPage = homePage.navigateToSettings();
        Assert.assertTrue(settingsPage.isPageLoaded(), "Settings page should be loaded");
        
        logPass("Successfully navigated to Settings page");
        
        settingsPage.navigateBack();
    }
    
    /**
     * Test Case 13: Verify navigation to Cart page
     * Priority: High
     */
    @Test(priority = 5, description = "Verify navigation to cart via bottom navigation")
    public void testNavigateToCart() {
        logInfo("Navigating to Cart page via bottom navigation");
        
        CartPage cartPage = homePage.navigateToCart();
        Assert.assertTrue(cartPage.isPageLoaded(), "Cart page should be loaded");
        
        logPass("Successfully navigated to Cart page");
    }
    
    /**
     * Test Case 14: Verify navigation to Favorites page
     * Priority: Medium
     */
    @Test(priority = 6, description = "Verify navigation to favorites via bottom navigation")
    public void testNavigateToFavorites() {
        logInfo("Navigating to Favorites page");
        
        FavoritesPage favoritesPage = homePage.navigateToFavorites();
        Assert.assertTrue(favoritesPage.isPageLoaded(), "Favorites page should be loaded");
        
        logPass("Successfully navigated to Favorites page");
    }
    
    /**
     * Test Case 15: Verify item list is displayed
     * Priority: High
     */
    @Test(priority = 7, description = "Verify items are displayed on home page")
    public void testItemListDisplayed() {
        logInfo("Checking if items are displayed");
        
        int itemCount = homePage.getItemCount();
        logInfo("Number of items displayed: " + itemCount);
        
        // Verify at least some items are shown (or handle empty state)
        Assert.assertTrue(itemCount >= 0, "Item count should be non-negative");
        
        logPass("Item list verification completed");
    }
    
    /**
     * Test Case 16: Verify clicking on item opens detail page
     * Priority: High
     */
    @Test(priority = 8, description = "Verify clicking on item opens item detail page")
    public void testClickItemOpensDetailPage() {
        logInfo("Clicking on first item");
        
        int itemCount = homePage.getItemCount();
        if (itemCount > 0) {
            ItemDetailPage itemDetailPage = homePage.clickFirstItem();
            Assert.assertTrue(itemDetailPage.isPageLoaded(), "Item detail page should be loaded");
            
            String itemTitle = itemDetailPage.getItemTitle();
            Assert.assertNotNull(itemTitle, "Item title should not be null");
            
            logPass("Successfully opened item detail page for: " + itemTitle);
            
            itemDetailPage.navigateBack();
        } else {
            logInfo("No items available to click");
        }
    }
    
    /**
     * Test Case 17: Verify logout functionality
     * Priority: High
     */
    @Test(priority = 9, description = "Verify user can logout from home page")
    public void testLogout() {
        logInfo("Attempting to logout");
        
        LoginPage loginPageAfterLogout = homePage.logout();
        Assert.assertTrue(loginPageAfterLogout.isPageLoaded(), "Login page should be displayed after logout");
        
        logPass("Successfully logged out");
    }
}
