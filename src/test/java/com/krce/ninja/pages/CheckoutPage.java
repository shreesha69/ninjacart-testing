package com.krce.ninja.pages;

import com.krce.ninja.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutPage extends BasePage {

    private final By newAddressOption  = By.cssSelector("input[value='new']");
    private final By firstNameField    = By.id("input-payment-firstname");
    private final By lastNameField     = By.id("input-payment-lastname");
    private final By addressField      = By.id("input-payment-address-1");
    private final By cityField         = By.id("input-payment-city");
    private final By postcodeField     = By.id("input-payment-postcode");
    private final By continuePayment   = By.id("button-payment-address");
    private final By allErrors         = By.cssSelector(".text-danger");

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void selectNewAddress() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(newAddressOption));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].click();",
                driver.findElement(newAddressOption)
        );
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
    }

    public void submitEmptyBillingForm() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('input-payment-firstname').value='';");
        js.executeScript("document.getElementById('input-payment-lastname').value='';");
        js.executeScript("document.getElementById('input-payment-address-1').value='';");
        js.executeScript("document.getElementById('input-payment-city').value='';");
        js.executeScript("document.getElementById('input-payment-postcode').value='';");
        click(continuePayment);
    }

    public List<String> getAllErrorMessages() {
        waitForElement(allErrors);
        return driver.findElements(allErrors)
                .stream()
                .map(e -> e.getText())
                .filter(t -> !t.isEmpty())
                .collect(Collectors.toList());
    }

    public boolean hasFieldError(String fieldName) {
        return getAllErrorMessages().stream()
                .anyMatch(msg -> msg.toLowerCase()
                        .contains(fieldName.toLowerCase()));
    }
}