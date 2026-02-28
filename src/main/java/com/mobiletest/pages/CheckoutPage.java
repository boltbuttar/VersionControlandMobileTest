package com.mobiletest.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * CheckoutPage handles the checkout process.
 */
public class CheckoutPage extends BasePage {
    
    @AndroidFindBy(id = "com.example.app:id/checkout_toolbar")
    private WebElement checkoutToolbar;
    
    @AndroidFindBy(id = "com.example.app:id/shipping_address_input")
    private WebElement shippingAddressInput;
    
    @AndroidFindBy(id = "com.example.app:id/payment_method_selector")
    private WebElement paymentMethodSelector;
    
    @AndroidFindBy(id = "com.example.app:id/order_summary")
    private WebElement orderSummary;
    
    @AndroidFindBy(id = "com.example.app:id/total_amount")
    private WebElement totalAmount;
    
    @AndroidFindBy(id = "com.example.app:id/place_order_button")
    private WebElement placeOrderButton;
    
    @AndroidFindBy(id = "com.example.app:id/order_confirmation")
    private WebElement orderConfirmation;
    
    private final By checkoutToolbarLocator = By.id("com.example.app:id/checkout_toolbar");
    private final By orderConfirmationLocator = By.id("com.example.app:id/order_confirmation");
    
    public CheckoutPage(AndroidDriver driver) {
        super(driver);
    }
    
    @Override
    public boolean isPageLoaded() {
        return isDisplayed(checkoutToolbarLocator);
    }
    
    public CheckoutPage enterShippingAddress(String address) {
        enterText(shippingAddressInput, address);
        return this;
    }
    
    public CheckoutPage selectPaymentMethod() {
        click(paymentMethodSelector);
        return this;
    }
    
    public String getTotalAmount() {
        return getText(totalAmount);
    }
    
    public CheckoutPage placeOrder() {
        click(placeOrderButton);
        return this;
    }
    
    public boolean isOrderConfirmed() {
        return isDisplayed(orderConfirmationLocator);
    }
    
    public String getOrderConfirmationMessage() {
        return getText(orderConfirmation);
    }
}
