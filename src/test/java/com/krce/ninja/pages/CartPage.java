package com.krce.ninja.pages;

import com.krce.ninja.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

    // --- Locators ---
    private final By cartDiv        = By.id("cart");
    private final By viewCartLink   = By.cssSelector("#cart ul li a[href*='cart']");
    private final By checkoutButton = By.linkText("Checkout");
    private final By emptyCartMsg   = By.cssSelector("#content p");
    private final By productName    = By.cssSelector("#content table td:nth-child(2) a");
    private final By quantityInput  = By.cssSelector("input.form-control");
    private final By updateButton   = By.cssSelector("button[data-original-title='Update']");
    private final By removeButton   = By.cssSelector("button[data-original-title='Remove']");
    private final By unitPrice      = By.cssSelector("#content table td:nth-child(5)");
    private final By lineTotal      = By.cssSelector("#content table td:nth-child(6)");

    // --- Constructor ---
    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // --- Actions ---

    // Opens cart dropdown and clicks View Cart
    public void openCart() {
        WebElement cart = wait.until(
                ExpectedConditions.presenceOfElementLocated(cartDiv)
        );
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", cart);
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartLink));
        click(viewCartLink);
    }

    public void proceedToCheckout() {
        click(checkoutButton);
    }

    // Updates quantity of first product in cart
    public void updateQuantity(String qty) {
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(quantityInput)
        );
        input.clear();
        input.sendKeys(qty);
        click(updateButton);
    }

    // Removes first product from cart
    public void removeProduct() {
        click(removeButton);
    }

    // --- Validations ---
    public String getProductName() {
        return getText(productName);
    }

    public String getEmptyCartMessage() {
        return getText(emptyCartMsg);
    }

    public String getLineTotal() {
        return getText(lineTotal);
    }

    public String getUnitPrice() {
        return getText(unitPrice);
    }
}