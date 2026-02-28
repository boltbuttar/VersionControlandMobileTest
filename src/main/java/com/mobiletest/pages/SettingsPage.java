package com.mobiletest.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * SettingsPage handles application settings and preferences.
 */
public class SettingsPage extends BasePage {
    
    @AndroidFindBy(id = "com.example.app:id/settings_toolbar")
    private WebElement settingsToolbar;
    
    @AndroidFindBy(id = "com.example.app:id/notifications_toggle")
    private WebElement notificationsToggle;
    
    @AndroidFindBy(id = "com.example.app:id/dark_mode_toggle")
    private WebElement darkModeToggle;
    
    @AndroidFindBy(id = "com.example.app:id/language_selector")
    private WebElement languageSelector;
    
    @AndroidFindBy(id = "com.example.app:id/privacy_settings")
    private WebElement privacySettings;
    
    @AndroidFindBy(id = "com.example.app:id/account_settings")
    private WebElement accountSettings;
    
    @AndroidFindBy(id = "com.example.app:id/about_app")
    private WebElement aboutApp;
    
    @AndroidFindBy(id = "com.example.app:id/logout_button")
    private WebElement logoutButton;
    
    @AndroidFindBy(id = "com.example.app:id/back_button")
    private WebElement backButton;
    
    @AndroidFindBy(id = "com.example.app:id/app_version")
    private WebElement appVersion;
    
    private final By settingsToolbarLocator = By.id("com.example.app:id/settings_toolbar");
    
    public SettingsPage(AndroidDriver driver) {
        super(driver);
    }
    
    @Override
    public boolean isPageLoaded() {
        return isDisplayed(settingsToolbarLocator);
    }
    
    public SettingsPage toggleNotifications() {
        click(notificationsToggle);
        return this;
    }
    
    public boolean isNotificationsEnabled() {
        return "true".equals(getAttribute(notificationsToggle, "checked"));
    }
    
    public SettingsPage toggleDarkMode() {
        click(darkModeToggle);
        return this;
    }
    
    public boolean isDarkModeEnabled() {
        return "true".equals(getAttribute(darkModeToggle, "checked"));
    }
    
    public SettingsPage clickLanguageSelector() {
        click(languageSelector);
        return this;
    }
    
    public SettingsPage clickPrivacySettings() {
        click(privacySettings);
        return this;
    }
    
    public SettingsPage clickAccountSettings() {
        click(accountSettings);
        return this;
    }
    
    public SettingsPage clickAboutApp() {
        click(aboutApp);
        return this;
    }
    
    public String getAppVersion() {
        return getText(appVersion);
    }
    
    public LoginPage logout() {
        click(logoutButton);
        return new LoginPage(driver);
    }
    
    public HomePage navigateBack() {
        click(backButton);
        return new HomePage(driver);
    }
}
