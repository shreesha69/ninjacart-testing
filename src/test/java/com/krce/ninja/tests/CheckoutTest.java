package com.krce.ninja.tests;

import com.krce.ninja.base.BaseTest;
import com.krce.ninja.pages.CartPage;
import com.krce.ninja.pages.CheckoutPage;
import com.krce.ninja.pages.HomePage;
import com.krce.ninja.pages.LoginPage;
import com.krce.ninja.pages.ProductPage;
import com.krce.ninja.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    private static final String MACBOOK_URL =
            "https://tutorialsninja.com/demo/index.php?route=product/product&product_id=43";

    private static final String CART_URL =
            "https://tutorialsninja.com/demo/index.php?route=checkout/cart";

    private static final String CHECKOUT_URL =
            "https://tutorialsninja.com/demo/index.php?route=checkout/checkout";

    @Test(priority = 1)
    public void testCheckoutAsLoggedInUser() {

        // Step 1 - login
        HomePage homePage = new HomePage(driver, wait);
        homePage.navigateToLogin();

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login(
                ConfigReader.getValidEmail(),
                ConfigReader.getValidPassword()
        );
        Assert.assertTrue(loginPage.isLoginSuccessful(),
                "Login failed before checkout");

        // Step 2 - go to product and add to cart
        driver.get(MACBOOK_URL);
        ProductPage productPage = new ProductPage(driver, wait);
        productPage.addToCart();

        // Step 3 - navigate directly to cart page
        driver.get(CART_URL);

        // Step 4 - proceed to checkout
        CartPage cartPage = new CartPage(driver, wait);
        cartPage.proceedToCheckout();

        // Step 5 - verify checkout page opened
        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout"),
                "Checkout page not opened. URL: " + driver.getCurrentUrl()
        );
    }

    @Test(priority = 2)
    public void testCheckoutWithoutLogin() {

        // Step 1 - go to product and add to cart without login
        driver.get(MACBOOK_URL);
        ProductPage productPage = new ProductPage(driver, wait);
        productPage.addToCart();

        // Step 2 - navigate directly to cart page
        driver.get(CART_URL);

        // Step 3 - proceed to checkout
        CartPage cartPage = new CartPage(driver, wait);
        cartPage.proceedToCheckout();

        // Step 4 - verify on checkout or login page
        String url = driver.getCurrentUrl();
        Assert.assertTrue(
                url.contains("checkout") || url.contains("login"),
                "Expected checkout or login page. Got: " + url
        );
    }
}