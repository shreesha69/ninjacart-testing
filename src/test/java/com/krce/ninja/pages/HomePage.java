package com.krce.ninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "a[title='My Account']")
    WebElement myAccount;

    @FindBy(css = "a[href*='account/login']")
    WebElement loginLink;

    @FindBy(css = "a[href*='account/logout']")
    WebElement logoutLink;

    @FindBy(css = "a[href*='account/register']")
    WebElement registerLink;

    @FindBy(name = "search")
    WebElement searchBox;

    @FindBy(css = "button[class*='search']")
    WebElement searchBtn;

    @FindBy(css = "#content h2:first-of-type")
    WebElement myAccountText;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait   = wait;
        PageFactory.initElements(driver, this);
    }

    public void navigateToLogin() {
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(myAccount));
        actions.moveToElement(myAccount).click().perform();
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    public void navigateToRegister() {
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(myAccount));
        actions.moveToElement(myAccount).click().perform();
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccount)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }

    public void searchProduct(String keyword) {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.clear();
        searchBox.sendKeys(keyword);
        searchBtn.click();
    }

    public boolean isLoginSuccessful() {
        return wait.until(ExpectedConditions.visibilityOf(myAccountText)).isDisplayed();
    }
}