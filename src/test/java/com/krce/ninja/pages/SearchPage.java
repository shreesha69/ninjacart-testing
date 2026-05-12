package com.krce.ninja.pages;

import com.krce.ninja.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {

    private final By productList  = By.cssSelector(".product-thumb");
    private final By noResultMsg  = By.cssSelector("#content > p");
    private final By productNames = By.cssSelector(".caption h4 a");

    // Add to cart button inside each product thumb
    private final By addToCartBtn = By.cssSelector(
            ".product-thumb .btn-default"
    );

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

    public void addFirstProductToCart() {
        // Wait for results then click first add to cart button
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartBtn));
        driver.findElements(addToCartBtn).get(0).click();

        // Wait for success alert to confirm item added
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".alert-success")
        ));
    }
}