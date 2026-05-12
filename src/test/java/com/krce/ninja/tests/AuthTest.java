package com.krce.ninja.tests;

import com.krce.ninja.base.BaseTest;
import com.krce.ninja.pages.HomePage;
import com.krce.ninja.pages.LoginPage;
import com.krce.ninja.pages.RegisterPage;
import com.krce.ninja.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

@Listeners(com.krce.ninja.utils.ScreenshotListener.class)
public class AuthTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {ConfigReader.getValidEmail(), ConfigReader.getValidPassword(), true},
                {"wrong@test.com",             "wrong123",                     false},
        };
    }

    @Test(priority = 1, dataProvider = "loginData")
    public void testLogin(String email, String password, boolean expectSuccess) {
        HomePage homePage = new HomePage(driver, wait);
        homePage.navigateToLogin();

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login(email, password);

        if (expectSuccess) {
            Assert.assertTrue(loginPage.isLoginSuccessful(),
                    "Valid login failed");
        } else {
            Assert.assertTrue(loginPage.getErrorMessage().contains("Warning"),
                    "Error message not shown for invalid login");
        }
    }

    @Test(priority = 2)
    public void testLogout() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.navigateToLogin();

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login(ConfigReader.getValidEmail(), ConfigReader.getValidPassword());

        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed before logout");

        homePage.clickLogout();

        Assert.assertTrue(driver.getCurrentUrl().contains("logout"),
                "Logout redirect failed");

       // Assert.fail("Forced failure to test screenshot");

    }

    @Test(priority = 3)
    public void testRegister() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.navigateToRegister();

        RegisterPage registerPage = new RegisterPage(driver, wait);
        String uniqueEmail = "ninja_" + System.currentTimeMillis() + "@gmail.com";

        registerPage.register(
                "Shree", "Ninja",
                uniqueEmail,
                "9876543210",
                "Ninja@1234"
        );

        Assert.assertTrue(registerPage.isRegistrationSuccessful(),
                "Registration failed");
    }
}