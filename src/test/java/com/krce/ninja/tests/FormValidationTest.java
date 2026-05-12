package com.krce.ninja.tests;

import com.krce.ninja.base.BaseTest;
import com.krce.ninja.pages.HomePage;
import com.krce.ninja.pages.RegisterPage;
import com.krce.ninja.utils.ConfigReader;
import com.krce.ninja.pages.LoginPage;
import com.krce.ninja.pages.CartPage;
import com.krce.ninja.pages.SearchPage;
import com.krce.ninja.pages.CheckoutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * FormValidationTest - Module 5
 * Tests form validations across the application
 */
public class FormValidationTest extends BaseTest {

    /**
     * Test 1 - Empty registration form
     * Submit empty form and verify error messages
     */
    @Test(priority = 1)
    public void testEmptyRegistrationForm() {

        HomePage homePage = new HomePage(driver, wait);
        homePage.navigateToRegister();

        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.submitEmpty();

        Assert.assertTrue(
                registerPage.isFirstNameErrorDisplayed(),
                "First name error not shown"
        );
        Assert.assertTrue(
                registerPage.isLastNameErrorDisplayed(),
                "Last name error not shown"
        );
        Assert.assertTrue(
                registerPage.isPasswordErrorDisplayed(),
                "Password error not shown"
        );
    }

    /**
     * Test 2 - Invalid email format during registration
     * Enter invalid email and verify validation
     */
    @Test(priority = 2)
    public void testInvalidEmailFormat() {

        HomePage homePage = new HomePage(driver, wait);
        homePage.navigateToRegister();

        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.register(
                "Shree", "Test",
                "invalidemail",
                "9876543210",
                "Test@1234"
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("register") ||
                        registerPage.isEmailErrorDisplayed(),
                "Invalid email should show error or stay on register page"
        );
    }
}