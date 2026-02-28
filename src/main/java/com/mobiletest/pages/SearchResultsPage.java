package com.mobiletest.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * SearchResultsPage displays search results.
 */
public class SearchResultsPage extends BasePage {
    
    @AndroidFindBy(id = "com.example.app:id/search_results_title")
    private WebElement resultsTitle;
    
    @AndroidFindBy(id = "com.example.app:id/results_count")
    private WebElement resultsCount;
    
    @AndroidFindBy(id = "com.example.app:id/result_item")
    private List<WebElement> resultItems;
    
    @AndroidFindBy(id = "com.example.app:id/no_results_message")
    private WebElement noResultsMessage;
    
    @AndroidFindBy(id = "com.example.app:id/filter_button")
    private WebElement filterButton;
    
    @AndroidFindBy(id = "com.example.app:id/sort_button")
    private WebElement sortButton;
    
    private final By resultsTitleLocator = By.id("com.example.app:id/search_results_title");
    private final By resultItemsLocator = By.id("com.example.app:id/result_item");
    private final By noResultsLocator = By.id("com.example.app:id/no_results_message");
    
    public SearchResultsPage(AndroidDriver driver) {
        super(driver);
    }
    
    @Override
    public boolean isPageLoaded() {
        return isDisplayed(resultsTitleLocator);
    }
    
    public int getResultsCount() {
        return findElements(resultItemsLocator).size();
    }
    
    public String getResultsCountText() {
        return getText(resultsCount);
    }
    
    public boolean hasResults() {
        return getResultsCount() > 0;
    }
    
    public boolean isNoResultsDisplayed() {
        return isDisplayed(noResultsLocator);
    }
    
    public ItemDetailPage clickFirstResult() {
        if (!resultItems.isEmpty()) {
            click(resultItems.get(0));
        }
        return new ItemDetailPage(driver);
    }
    
    public ItemDetailPage clickResultAtIndex(int index) {
        List<WebElement> items = findElements(resultItemsLocator);
        if (index < items.size()) {
            click(items.get(index));
        }
        return new ItemDetailPage(driver);
    }
    
    public SearchResultsPage clickFilter() {
        click(filterButton);
        return this;
    }
    
    public SearchResultsPage clickSort() {
        click(sortButton);
        return this;
    }
    
    public HomePage navigateBack() {
        navigateBack();
        return new HomePage(driver);
    }
}
