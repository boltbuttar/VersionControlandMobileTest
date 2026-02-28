package com.mobiletest.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * NotificationPage displays user notifications.
 */
public class NotificationPage extends BasePage {
    
    @AndroidFindBy(id = "com.example.app:id/notification_toolbar")
    private WebElement notificationToolbar;
    
    @AndroidFindBy(id = "com.example.app:id/notification_item")
    private List<WebElement> notificationItems;
    
    @AndroidFindBy(id = "com.example.app:id/mark_all_read_button")
    private WebElement markAllReadButton;
    
    @AndroidFindBy(id = "com.example.app:id/no_notifications_message")
    private WebElement noNotificationsMessage;
    
    private final By notificationToolbarLocator = By.id("com.example.app:id/notification_toolbar");
    private final By notificationItemsLocator = By.id("com.example.app:id/notification_item");
    
    public NotificationPage(AndroidDriver driver) {
        super(driver);
    }
    
    @Override
    public boolean isPageLoaded() {
        return isDisplayed(notificationToolbarLocator);
    }
    
    public int getNotificationCount() {
        return findElements(notificationItemsLocator).size();
    }
    
    public boolean hasNotifications() {
        return getNotificationCount() > 0;
    }
    
    public NotificationPage markAllAsRead() {
        click(markAllReadButton);
        return this;
    }
    
    public HomePage navigateBack() {
        navigateBack();
        return new HomePage(driver);
    }
}
