package com.krce.ninja.pages;

import com.krce.ninja.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

    // --- Locators ---
    private final By productName  = By.cssSelector("div.col-sm-4 h1");
    private final By productPrice = By.cssSelector("#content h2.price");
    private final By addToCartBtn = By.id("button-cart");
    private final By successAlert = By.cssSelector(".alert-success");

    // --- Constructor ---
    public ProductPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // --- Actions ---
    public String getProductName() {
        return getText(productName);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    // Adds product to cart and waits for success message
    public void addToCart() {
        click(addToCartBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(successAlert));
    }

    public String getSuccessMessage() {
        return getText(successAlert);
    }
}