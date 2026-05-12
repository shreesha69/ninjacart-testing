package com.krce.ninja.pages;

import com.krce.ninja.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class RegisterPage extends BasePage {

    private final By firstNameField = By.id("input-firstname");
    private final By lastNameField  = By.id("input-lastname");
    private final By emailField     = By.id("input-email");
    private final By phoneField     = By.id("input-telephone");
    private final By passwordField  = By.id("input-password");
    private final By confirmField   = By.id("input-confirm");
    private final By privacyPolicy  = By.name("agree");
    private final By submitButton   = By.cssSelector("input[value='Continue']");
    private final By allErrors      = By.cssSelector(".text-danger");
    private final By successHeading = By.cssSelector("#content h1");

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void register(String firstName, String lastName,
                         String email, String phone, String password) {
        type(firstNameField, firstName);
        type(lastNameField,  lastName);
        type(emailField,     email);
        type(phoneField,     phone);
        type(passwordField,  password);
        type(confirmField,   password);
        click(privacyPolicy);
        click(submitButton);
    }

    public void submitEmptyForm() {
        click(privacyPolicy);
        click(submitButton);
    }

    public void submitWithInvalidEmail(String invalidEmail) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        waitForElement(firstNameField);

        js.executeScript("document.getElementById('input-firstname').value='Test';");
        js.executeScript("document.getElementById('input-lastname').value='User';");
        js.executeScript("document.getElementById('input-telephone').value='9876543210';");
        js.executeScript("document.getElementById('input-password').value='Test@1234';");
        js.executeScript("document.getElementById('input-confirm').value='Test@1234';");
        js.executeScript("document.getElementById('input-email').value=arguments[0];", invalidEmail);

        WebElement agreeCheckbox = driver.findElement(privacyPolicy);
        if (!agreeCheckbox.isSelected()) {
            js.executeScript("arguments[0].click();", agreeCheckbox);
        }

        js.executeScript("document.querySelector(\"input[value='Continue']\").form.submit();");
    }

    public List<String> getAllErrorMessages() {
        waitForElement(allErrors);
        return driver.findElements(allErrors)
                .stream()
                .map(e -> e.getText())
                .filter(t -> !t.isEmpty())
                .collect(Collectors.toList());
    }

    public boolean isRegistrationSuccessful() {
        return getText(successHeading).contains("Your Account Has Been Created");
    }
}