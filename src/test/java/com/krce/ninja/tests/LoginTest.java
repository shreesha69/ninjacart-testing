package com.krce.ninja.tests;

import com.krce.ninja.BaseTest;
import com.krce.ninja.pages.HomePage;
import com.krce.ninja.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void navigateToLogin() {

        driver.get("https://tutorialsninja.com/demo/");

        HomePage home = new HomePage(driver, wait);
        home.navigateToLogin();
    }

    @Test
    public void testInvalidLogin() {

        driver.get("https://tutorialsninja.com/demo/");

        HomePage home = new HomePage(driver, wait);
        home.navigateToLogin();

        LoginPage login = new LoginPage(driver);

        login.enterEmail("wrong@test.com");
        login.enterPassword("wrong123");
        login.clickLogin();

        String error = login.getErrorMessage();

        Assert.assertTrue(error.contains("Warning"));
    }
}