package com.krce.ninja.pages;

import com.krce.ninja.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * CheckoutPage - handles all checkout flow actions
 */
public class CheckoutPage extends BasePage {

    // --- Locators ---
    private final By guestRadio      = By.cssSelector("input[value='guest']");
    private final By continueGuest   = By.id("button-account");
    private final By firstNameField  = By.id("input-payment-firstname");
    private final By lastNameField   = By.id("input-payment-lastname");
    private final By emailField      = By.id("input-payment-email");
    private final By telephoneField  = By.id("input-payment-telephone");
    private final By addressField    = By.id("input-payment-address-1");
    private final By cityField       = By.id("input-payment-city");
    private final By postcodeField   = By.id("input-payment-postcode");
    private final By continuePayment = By.id("button-guest");
    private final By continueShipping = By.id("button-shipping-method");
    private final By agreeCheck      = By.name("agree");
    private final By confirmButton   = By.id("button-confirm");
    private final By successHeading  = By.cssSelector("#content h1");
    private final By loginPageHeader = By.cssSelector("#content h2");

    // --- Constructor ---
    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // --- Actions ---

    // Selects guest checkout option
    public void selectGuestCheckout() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(guestRadio));
        click(guestRadio);
        click(continueGuest);
    }

    // Fills all delivery details for guest
    public void fillGuestDetails(String first, String last,
                                 String email, String phone,
                                 String address, String city,
                                 String postcode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        type(firstNameField,  first);
        type(lastNameField,   last);
        type(emailField,      email);
        type(telephoneField,  phone);
        type(addressField,    address);
        type(cityField,       city);
        type(postcodeField,   postcode);
        click(continuePayment);
    }

    // Confirms the final order
    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShipping));
        click(continueShipping);
        wait.until(ExpectedConditions.elementToBeClickable(agreeCheck));
        click(agreeCheck);
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        click(confirmButton);
    }

    // --- Validations ---
    public String getSuccessMessage() {
        return getText(successHeading);
    }

    public boolean isOnLoginPage() {
        return isDisplayed(loginPageHeader);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}