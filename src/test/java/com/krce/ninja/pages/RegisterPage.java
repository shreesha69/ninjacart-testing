package com.krce.ninja.pages;

import com.krce.ninja.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {

    private final By firstName    = By.id("input-firstname");
    private final By lastName     = By.id("input-lastname");
    private final By email        = By.id("input-email");
    private final By telephone    = By.id("input-telephone");
    private final By password     = By.id("input-password");
    private final By confirmPass  = By.id("input-confirm");
    private final By agreeCheck   = By.name("agree");
    private final By continueBtn  = By.cssSelector("input[value='Continue']");
    private final By successMsg   = By.cssSelector("#content h1");

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void register(String first, String last,
                         String mail, String phone, String pass) {
        type(firstName,   first);
        type(lastName,    last);
        type(email,       mail);
        type(telephone,   phone);
        type(password,    pass);
        type(confirmPass, pass);
        click(agreeCheck);
        click(continueBtn);
    }

    public boolean isRegistrationSuccessful() {
        return getText(successMsg).contains("Your Account Has Been Created");
    }
}