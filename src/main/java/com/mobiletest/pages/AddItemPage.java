package com.mobiletest.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * AddItemPage handles adding new items (for admin/seller functionality).
 */
public class AddItemPage extends BasePage {
    
    @AndroidFindBy(id = "com.example.app:id/add_item_toolbar")
    private WebElement addItemToolbar;
    
    @AndroidFindBy(id = "com.example.app:id/item_name_input")
    private WebElement itemNameInput;
    
    @AndroidFindBy(id = "com.example.app:id/item_description_input")
    private WebElement itemDescriptionInput;
    
    @AndroidFindBy(id = "com.example.app:id/item_price_input")
    private WebElement itemPriceInput;
    
    @AndroidFindBy(id = "com.example.app:id/add_image_button")
    private WebElement addImageButton;
    
    @AndroidFindBy(id = "com.example.app:id/save_item_button")
    private WebElement saveItemButton;
    
    @AndroidFindBy(id = "com.example.app:id/cancel_button")
    private WebElement cancelButton;
    
    private final By addItemToolbarLocator = By.id("com.example.app:id/add_item_toolbar");
    
    public AddItemPage(AndroidDriver driver) {
        super(driver);
    }
    
    @Override
    public boolean isPageLoaded() {
        return isDisplayed(addItemToolbarLocator);
    }
    
    public AddItemPage enterItemName(String name) {
        enterText(itemNameInput, name);
        return this;
    }
    
    public AddItemPage enterDescription(String description) {
        enterText(itemDescriptionInput, description);
        return this;
    }
    
    public AddItemPage enterPrice(String price) {
        enterText(itemPriceInput, price);
        return this;
    }
    
    public AddItemPage addImage() {
        click(addImageButton);
        return this;
    }
    
    public HomePage saveItem() {
        click(saveItemButton);
        return new HomePage(driver);
    }
    
    public HomePage cancel() {
        click(cancelButton);
        return new HomePage(driver);
    }
    
    public HomePage addNewItem(String name, String description, String price) {
        enterItemName(name);
        enterDescription(description);
        enterPrice(price);
        return saveItem();
    }
}
