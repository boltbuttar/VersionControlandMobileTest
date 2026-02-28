package com.mobiletest.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * ProfilePage handles user profile display and editing.
 */
public class ProfilePage extends BasePage {
    
    @AndroidFindBy(id = "com.example.app:id/profile_toolbar")
    private WebElement profileToolbar;
    
    @AndroidFindBy(id = "com.example.app:id/profile_image")
    private WebElement profileImage;
    
    @AndroidFindBy(id = "com.example.app:id/username_text")
    private WebElement usernameText;
    
    @AndroidFindBy(id = "com.example.app:id/email_text")
    private WebElement emailText;
    
    @AndroidFindBy(id = "com.example.app:id/phone_text")
    private WebElement phoneText;
    
    @AndroidFindBy(id = "com.example.app:id/edit_profile_button")
    private WebElement editProfileButton;
    
    @AndroidFindBy(id = "com.example.app:id/change_password_button")
    private WebElement changePasswordButton;
    
    @AndroidFindBy(id = "com.example.app:id/back_button")
    private WebElement backButton;
    
    @AndroidFindBy(id = "com.example.app:id/edit_username_input")
    private WebElement editUsernameInput;
    
    @AndroidFindBy(id = "com.example.app:id/edit_email_input")
    private WebElement editEmailInput;
    
    @AndroidFindBy(id = "com.example.app:id/save_button")
    private WebElement saveButton;
    
    @AndroidFindBy(id = "com.example.app:id/cancel_button")
    private WebElement cancelButton;
    
    private final By profileToolbarLocator = By.id("com.example.app:id/profile_toolbar");
    
    public ProfilePage(AndroidDriver driver) {
        super(driver);
    }
    
    @Override
    public boolean isPageLoaded() {
        return isDisplayed(profileToolbarLocator);
    }
    
    public String getUsername() {
        return getText(usernameText);
    }
    
    public String getEmail() {
        return getText(emailText);
    }
    
    public String getPhone() {
        return getText(phoneText);
    }
    
    public boolean isProfileImageDisplayed() {
        return isDisplayed(profileImage);
    }
    
    public ProfilePage clickEditProfile() {
        click(editProfileButton);
        return this;
    }
    
    public ProfilePage editUsername(String newUsername) {
        clickEditProfile();
        enterText(editUsernameInput, newUsername);
        return this;
    }
    
    public ProfilePage editEmail(String newEmail) {
        enterText(editEmailInput, newEmail);
        return this;
    }
    
    public ProfilePage saveChanges() {
        click(saveButton);
        return this;
    }
    
    public ProfilePage cancelEdit() {
        click(cancelButton);
        return this;
    }
    
    public ProfilePage clickChangePassword() {
        click(changePasswordButton);
        return this;
    }
    
    public HomePage navigateBack() {
        click(backButton);
        return new HomePage(driver);
    }
}
