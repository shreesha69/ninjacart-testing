package com.krce.ninja.tests;

import com.krce.ninja.base.BaseTest;
import com.krce.ninja.pages.CartPage;
import com.krce.ninja.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private static final String MACBOOK_URL =
            "https://tutorialsninja.com/demo/index.php?route=product/product&product_id=43";

    private static final String CART_URL =
            "https://tutorialsninja.com/demo/index.php?route=checkout/cart";

    @Test(priority = 1)
    public void testAddProductToCart() {

        // Step 1 - go to product page
        driver.get(MACBOOK_URL);
        ProductPage productPage = new ProductPage(driver, wait);

        // Step 2 - get product name and price before adding
        String expectedName = productPage.getProductName();

        // Step 3 - add to cart
        productPage.addToCart();
        Assert.assertTrue(
                productPage.getSuccessMessage().contains("Success"),
                "Add to cart success message not shown"
        );

        // Step 4 - go to cart and verify product
        driver.get(CART_URL);
        CartPage cartPage = new CartPage(driver, wait);

        Assert.assertTrue(
                cartPage.getProductName().contains("MacBook"),
                "Product not found in cart. Expected MacBook but got: "
                        + cartPage.getProductName()
        );
    }

    @Test(priority = 2)
    public void testRemoveProductFromCart() {

        // Step 1 - add product to cart
        driver.get(MACBOOK_URL);
        ProductPage productPage = new ProductPage(driver, wait);
        productPage.addToCart();

        // Step 2 - go to cart
        driver.get(CART_URL);
        CartPage cartPage = new CartPage(driver, wait);

        // Step 3 - remove product
        cartPage.removeProduct();

        // Step 4 - verify cart is empty
        Assert.assertTrue(
                cartPage.getEmptyCartMessage().contains("Your shopping cart is empty"),
                "Cart not empty after removing product"
        );
    }
}