package com.mobiletest.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * CartPage handles shopping cart functionality.
 */
public class CartPage extends BasePage {
    
    @AndroidFindBy(id = "com.example.app:id/cart_toolbar")
    private WebElement cartToolbar;
    
    @AndroidFindBy(id = "com.example.app:id/cart_item")
    private List<WebElement> cartItems;
    
    @AndroidFindBy(id = "com.example.app:id/cart_total")
    private WebElement cartTotal;
    
    @AndroidFindBy(id = "com.example.app:id/checkout_button")
    private WebElement checkoutButton;
    
    @AndroidFindBy(id = "com.example.app:id/clear_cart_button")
    private WebElement clearCartButton;
    
    @AndroidFindBy(id = "com.example.app:id/empty_cart_message")
    private WebElement emptyCartMessage;
    
    private final By cartToolbarLocator = By.id("com.example.app:id/cart_toolbar");
    private final By cartItemsLocator = By.id("com.example.app:id/cart_item");
    private final By emptyCartLocator = By.id("com.example.app:id/empty_cart_message");
    
    public CartPage(AndroidDriver driver) {
        super(driver);
    }
    
    @Override
    public boolean isPageLoaded() {
        return isDisplayed(cartToolbarLocator);
    }
    
    public int getCartItemCount() {
        return findElements(cartItemsLocator).size();
    }
    
    public boolean isCartEmpty() {
        return isDisplayed(emptyCartLocator);
    }
    
    public String getCartTotal() {
        return getText(cartTotal);
    }
    
    public CartPage clearCart() {
        click(clearCartButton);
        return this;
    }
    
    public CheckoutPage proceedToCheckout() {
        click(checkoutButton);
        return new CheckoutPage(driver);
    }
    
    public HomePage navigateBack() {
        navigateBack();
        return new HomePage(driver);
    }
}
