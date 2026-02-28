package com.mobiletest.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * ItemDetailPage displays detailed information about a specific item.
 */
public class ItemDetailPage extends BasePage {
    
    @AndroidFindBy(id = "com.example.app:id/item_title")
    private WebElement itemTitle;
    
    @AndroidFindBy(id = "com.example.app:id/item_description")
    private WebElement itemDescription;
    
    @AndroidFindBy(id = "com.example.app:id/item_price")
    private WebElement itemPrice;
    
    @AndroidFindBy(id = "com.example.app:id/item_image")
    private WebElement itemImage;
    
    @AndroidFindBy(id = "com.example.app:id/add_to_cart_button")
    private WebElement addToCartButton;
    
    @AndroidFindBy(id = "com.example.app:id/add_to_favorites_button")
    private WebElement addToFavoritesButton;
    
    @AndroidFindBy(id = "com.example.app:id/share_button")
    private WebElement shareButton;
    
    @AndroidFindBy(id = "com.example.app:id/quantity_input")
    private WebElement quantityInput;
    
    @AndroidFindBy(id = "com.example.app:id/increase_quantity")
    private WebElement increaseQuantityButton;
    
    @AndroidFindBy(id = "com.example.app:id/decrease_quantity")
    private WebElement decreaseQuantityButton;
    
    @AndroidFindBy(id = "com.example.app:id/back_button")
    private WebElement backButton;
    
    private final By itemTitleLocator = By.id("com.example.app:id/item_title");
    
    public ItemDetailPage(AndroidDriver driver) {
        super(driver);
    }
    
    @Override
    public boolean isPageLoaded() {
        return isDisplayed(itemTitleLocator);
    }
    
    public String getItemTitle() {
        return getText(itemTitle);
    }
    
    public String getItemDescription() {
        return getText(itemDescription);
    }
    
    public String getItemPrice() {
        return getText(itemPrice);
    }
    
    public boolean isItemImageDisplayed() {
        return isDisplayed(itemImage);
    }
    
    public ItemDetailPage addToCart() {
        click(addToCartButton);
        return this;
    }
    
    public ItemDetailPage addToFavorites() {
        click(addToFavoritesButton);
        return this;
    }
    
    public ItemDetailPage share() {
        click(shareButton);
        return this;
    }
    
    public ItemDetailPage increaseQuantity() {
        click(increaseQuantityButton);
        return this;
    }
    
    public ItemDetailPage decreaseQuantity() {
        click(decreaseQuantityButton);
        return this;
    }
    
    public String getQuantity() {
        return getText(quantityInput);
    }
    
    public ItemDetailPage setQuantity(int quantity) {
        enterText(quantityInput, String.valueOf(quantity));
        return this;
    }
    
    public HomePage navigateBack() {
        click(backButton);
        return new HomePage(driver);
    }
}
