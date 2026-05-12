package com.krce.ninja.pages;

import com.krce.ninja.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    private final By myAccountMenu = By.cssSelector("a.dropdown-toggle span.hidden-xs");
    private final By loginLink     = By.cssSelector("a[href*='account/login']");
    private final By registerLink  = By.cssSelector("a[href*='account/register']");
    private final By logoutLink    = By.cssSelector("a[href*='account/logout']");
    private final By searchInput   = By.cssSelector("div#search input[name='search']");
    private final By searchButton  = By.cssSelector("div#search button");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void searchProduct(String keyword) {
        wait.until(ExpectedConditions.titleContains("Your Store"));
        type(searchInput, keyword);
        click(searchButton);
    }

    public void navigateToLogin() {
        click(myAccountMenu);
        click(loginLink);
    }

    public void navigateToRegister() {
        click(myAccountMenu);
        click(registerLink);
    }

    public void clickLogout() {
        click(myAccountMenu);
        click(logoutLink);
    }
}