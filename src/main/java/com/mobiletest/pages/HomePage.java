package com.mobiletest.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * HomePage represents the main/home screen of the application.
 * Users land on this page after successful login.
 */
public class HomePage extends BasePage {
    
    // Element Locators
    @AndroidFindBy(id = "com.example.app:id/welcome_message")
    private WebElement welcomeMessage;
    
    @AndroidFindBy(id = "com.example.app:id/home_toolbar")
    private WebElement homeToolbar;
    
    @AndroidFindBy(id = "com.example.app:id/menu_button")
    private WebElement menuButton;
    
    @AndroidFindBy(id = "com.example.app:id/search_button")
    private WebElement searchButton;
    
    @AndroidFindBy(id = "com.example.app:id/search_input")
    private WebElement searchInput;
    
    @AndroidFindBy(id = "com.example.app:id/notification_icon")
    private WebElement notificationIcon;
    
    @AndroidFindBy(id = "com.example.app:id/profile_icon")
    private WebElement profileIcon;
    
    @AndroidFindBy(id = "com.example.app:id/settings_icon")
    private WebElement settingsIcon;
    
    @AndroidFindBy(id = "com.example.app:id/item_list")
    private WebElement itemList;
    
    @AndroidFindBy(id = "com.example.app:id/item_card")
    private List<WebElement> itemCards;
    
    @AndroidFindBy(id = "com.example.app:id/fab_add")
    private WebElement addButton;
    
    @AndroidFindBy(id = "com.example.app:id/bottom_navigation")
    private WebElement bottomNavigation;
    
    @AndroidFindBy(id = "com.example.app:id/nav_home")
    private WebElement navHome;
    
    @AndroidFindBy(id = "com.example.app:id/nav_favorites")
    private WebElement navFavorites;
    
    @AndroidFindBy(id = "com.example.app:id/nav_cart")
    private WebElement navCart;
    
    @AndroidFindBy(id = "com.example.app:id/nav_profile")
    private WebElement navProfile;
    
    @AndroidFindBy(id = "com.example.app:id/logout_button")
    private WebElement logoutButton;
    
    // By locators
    private final By welcomeMessageLocator = By.id("com.example.app:id/welcome_message");
    private final By homeToolbarLocator = By.id("com.example.app:id/home_toolbar");
    private final By itemCardsLocator = By.id("com.example.app:id/item_card");
    
    /**
     * Constructor
     * @param driver AndroidDriver instance
     */
    public HomePage(AndroidDriver driver) {
        super(driver);
    }
    
    /**
     * Verify if home page is loaded
     * @return true if home page is displayed
     */
    @Override
    public boolean isPageLoaded() {
        return isDisplayed(homeToolbarLocator);
    }
    
    /**
     * Get welcome message text
     * @return Welcome message
     */
    public String getWelcomeMessage() {
        return getText(welcomeMessage);
    }
    
    /**
     * Check if welcome message is displayed
     * @return true if welcome message is visible
     */
    public boolean isWelcomeMessageDisplayed() {
        return isDisplayed(welcomeMessageLocator);
    }
    
    /**
     * Click on menu button
     * @return HomePage instance for chaining
     */
    public HomePage clickMenuButton() {
        click(menuButton);
        return this;
    }
    
    /**
     * Click on search button
     * @return HomePage instance for chaining
     */
    public HomePage clickSearchButton() {
        click(searchButton);
        return this;
    }
    
    /**
     * Perform search
     * @param searchQuery Search query
     * @return SearchResultsPage instance
     */
    public SearchResultsPage search(String searchQuery) {
        click(searchButton);
        enterText(searchInput, searchQuery);
        // Submit search
        driver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(
                io.appium.java_client.android.nativekey.AndroidKey.ENTER));
        return new SearchResultsPage(driver);
    }
    
    /**
     * Navigate to profile page
     * @return ProfilePage instance
     */
    public ProfilePage navigateToProfile() {
        click(profileIcon);
        return new ProfilePage(driver);
    }
    
    /**
     * Navigate to settings page
     * @return SettingsPage instance
     */
    public SettingsPage navigateToSettings() {
        click(settingsIcon);
        return new SettingsPage(driver);
    }
    
    /**
     * Click on notification icon
     * @return NotificationPage instance
     */
    public NotificationPage clickNotifications() {
        click(notificationIcon);
        return new NotificationPage(driver);
    }
    
    /**
     * Get number of items displayed
     * @return Count of items
     */
    public int getItemCount() {
        return findElements(itemCardsLocator).size();
    }
    
    /**
     * Click on first item
     * @return ItemDetailPage instance
     */
    public ItemDetailPage clickFirstItem() {
        if (!itemCards.isEmpty()) {
            click(itemCards.get(0));
        }
        return new ItemDetailPage(driver);
    }
    
    /**
     * Click on item by index
     * @param index Item index (0-based)
     * @return ItemDetailPage instance
     */
    public ItemDetailPage clickItemAtIndex(int index) {
        List<WebElement> items = findElements(itemCardsLocator);
        if (index < items.size()) {
            click(items.get(index));
        }
        return new ItemDetailPage(driver);
    }
    
    /**
     * Click on add button (FAB)
     * @return AddItemPage instance
     */
    public AddItemPage clickAddButton() {
        click(addButton);
        return new AddItemPage(driver);
    }
    
    /**
     * Navigate to favorites via bottom nav
     * @return FavoritesPage instance
     */
    public FavoritesPage navigateToFavorites() {
        click(navFavorites);
        return new FavoritesPage(driver);
    }
    
    /**
     * Navigate to cart via bottom nav
     * @return CartPage instance
     */
    public CartPage navigateToCart() {
        click(navCart);
        return new CartPage(driver);
    }
    
    /**
     * Logout from application
     * @return LoginPage instance
     */
    public LoginPage logout() {
        click(menuButton);
        click(logoutButton);
        return new LoginPage(driver);
    }
    
    /**
     * Check if bottom navigation is displayed
     * @return true if bottom navigation is visible
     */
    public boolean isBottomNavigationDisplayed() {
        return isDisplayed(bottomNavigation);
    }
}
