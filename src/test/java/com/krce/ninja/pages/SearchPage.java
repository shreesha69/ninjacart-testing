package com.krce.ninja.pages;

import com.krce.ninja.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {

    private final By productList = By.cssSelector(".product-thumb");
    private final By noResultMsg = By.cssSelector("#content > p:not(:has(input))");
    private final By productNames = By.cssSelector(".caption h4 a");

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public int getResultCount() {
        return driver.findElements(productList).size();
    }

    public String getNoResultMessage() {
        return getText(noResultMsg);
    }

    public void clickFirstProduct() {
        waitForElement(productNames).click();
    }
}