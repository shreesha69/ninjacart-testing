package com.krce.ninja.tests;

import com.krce.ninja.base.BaseTest;
import com.krce.ninja.pages.CheckoutPage;
import com.krce.ninja.pages.HomePage;
import com.krce.ninja.pages.LoginPage;
import com.krce.ninja.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FormValidationTest extends BaseTest {

    @Test(priority = 1,
            description = "Verify error messages when registration form submitted empty")
    public void testEmptyRegistrationForm() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.navigateToRegister();

        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.submitEmptyForm();

        List<String> errors = registerPage.getAllErrorMessages();
        System.out.println("Errors found: " + errors);

        Assert.assertTrue(errors.stream().anyMatch(e ->
                        e.contains("First Name must be between")),
                "First Name error not shown");

        Assert.assertTrue(errors.stream().anyMatch(e ->
                        e.contains("Last Name must be between")),
                "Last Name error not shown");

        Assert.assertTrue(errors.stream().anyMatch(e ->
                        e.contains("E-Mail Address does not appear to be valid")),
                "Email error not shown");

        Assert.assertTrue(errors.stream().anyMatch(e ->
                        e.contains("Telephone must be between")),
                "Telephone error not shown");

        Assert.assertTrue(errors.stream().anyMatch(e ->
                        e.contains("Password must be between")),
                "Password error not shown");

        System.out.println("All empty field errors verified: " + errors.size() + " errors shown");
    }

    @Test(priority = 2,
            description = "Verify error when invalid email format entered during registration")
    @SuppressWarnings("SpellCheckingInspection")
    public void testInvalidEmailFormat() {
        String[] invalidEmails = {
                "notanemail",
                "missing@",
                "@nodomain.com",
                "double@@test.com"
        };

        for (String invalidEmail : invalidEmails) {
            driver.get(
                    "https://tutorialsninja.com/demo/index.php?route=account/register"
            );

            RegisterPage registerPage = new RegisterPage(driver, wait);
            registerPage.submitWithInvalidEmail(invalidEmail);

            List<String> errors = registerPage.getAllErrorMessages();
            System.out.println("Email [" + invalidEmail + "] Errors: " + errors);

            Assert.assertTrue(
                    errors.stream().anyMatch(e ->
                            e.contains("E-Mail Address does not appear to be valid")),
                    "Email validation error not shown for: [" + invalidEmail + "]"
            );
        }

        System.out.println("Invalid email format validation verified");
    }

    @Test(priority = 3,
            description = "Verify field-level errors when checkout submitted with empty address")
    @SuppressWarnings("SpellCheckingInspection")
    public void testCheckoutMissingAddress() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.navigateToLogin();

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("csa2262@krce.ac.in", "Shree@13");
        Assert.assertTrue(loginPage.isLoginSuccessful(),
                "Login failed before checkout test");

        driver.get(
                "https://tutorialsninja.com/demo/index.php?route=product/product&product_id=40"
        );
        driver.findElement(org.openqa.selenium.By.id("button-cart")).click();

        driver.get(
                "https://tutorialsninja.com/demo/index.php?route=checkout/checkout"
        );

        CheckoutPage checkoutPage = new CheckoutPage(driver, wait);
        checkoutPage.selectNewAddress();
        checkoutPage.submitEmptyBillingForm();

        List<String> errors = checkoutPage.getAllErrorMessages();
        System.out.println("Checkout errors: " + errors);

        Assert.assertTrue(
                checkoutPage.hasFieldError("First Name"),
                "First Name error not shown. Actual: " + errors);

        Assert.assertTrue(
                checkoutPage.hasFieldError("Last Name"),
                "Last Name error not shown. Actual: " + errors);

        Assert.assertTrue(
                checkoutPage.hasFieldError("Address"),
                "Address error not shown. Actual: " + errors);

        Assert.assertTrue(
                checkoutPage.hasFieldError("City"),
                "City error not shown. Actual: " + errors);

        System.out.println("All checkout field errors verified: " + errors.size() + " errors");
    }
}