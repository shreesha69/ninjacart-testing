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
    WebElement login;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void navigateToLogin() {

        Actions actions = new Actions(driver);

        wait.until(ExpectedConditions.visibilityOf(myAccount));
        actions.moveToElement(myAccount).click().perform();

        wait.until(ExpectedConditions.elementToBeClickable(login)).click();
    }
}