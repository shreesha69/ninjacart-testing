package com.krce.ninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "input-email")
    WebElement email;

    @FindBy(id = "input-password")
    WebElement password;

    @FindBy(css = "input[value='Login']")
    WebElement loginBtn;

    @FindBy(css = ".alert-danger")
    WebElement errorMsg;

    @FindBy(css = "#content h2")
    WebElement accountHeading;

    @FindBy(css = "a[title='My Account']")
    WebElement myAccountMenu;

    @FindBy(linkText = "Logout")
    WebElement logoutLink;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait   = wait;
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String userEmail) {
        wait.until(ExpectedConditions.visibilityOf(email));
        email.clear();
        email.sendKeys(userEmail);
    }

    public void enterPassword(String userPass) {
        password.clear();
        password.sendKeys(userPass);
    }

    public void clickLogin() {
        loginBtn.click();
    }


    public void login(String userEmail, String userPass) {
        enterEmail(userEmail);
        enterPassword(userPass);
        clickLogin();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMsg));
        return errorMsg.getText();
    }

    public boolean isLoginSuccessful() {
        wait.until(ExpectedConditions.visibilityOf(accountHeading));
        return accountHeading.isDisplayed();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccountMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}