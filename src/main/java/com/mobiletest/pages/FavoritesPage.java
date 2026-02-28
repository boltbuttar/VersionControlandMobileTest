package com.mobiletest.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * FavoritesPage displays user's favorite items.
 */
public class FavoritesPage extends BasePage {
    
    @AndroidFindBy(id = "com.example.app:id/favorites_toolbar")
    private WebElement favoritesToolbar;
    
    @AndroidFindBy(id = "com.example.app:id/favorite_item")
    private List<WebElement> favoriteItems;
    
    @AndroidFindBy(id = "com.example.app:id/no_favorites_message")
    private WebElement noFavoritesMessage;
    
    private final By favoritesToolbarLocator = By.id("com.example.app:id/favorites_toolbar");
    private final By favoriteItemsLocator = By.id("com.example.app:id/favorite_item");
    
    public FavoritesPage(AndroidDriver driver) {
        super(driver);
    }
    
    @Override
    public boolean isPageLoaded() {
        return isDisplayed(favoritesToolbarLocator);
    }
    
    public int getFavoritesCount() {
        return findElements(favoriteItemsLocator).size();
    }
    
    public boolean hasFavorites() {
        return getFavoritesCount() > 0;
    }
    
    public ItemDetailPage clickFavoriteAtIndex(int index) {
        List<WebElement> items = findElements(favoriteItemsLocator);
        if (index < items.size()) {
            click(items.get(index));
        }
        return new ItemDetailPage(driver);
    }
    
    public HomePage navigateBack() {
        navigateBack();
        return new HomePage(driver);
    }
}
