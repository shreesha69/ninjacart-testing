package com.krce.ninja.tests;

import com.krce.ninja.base.BaseTest;
import com.krce.ninja.pages.HomePage;
import com.krce.ninja.pages.ProductPage;
import com.krce.ninja.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @Test(priority = 1)
    public void testSearchProduct() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.searchProduct("MacBook");

        SearchPage searchPage = new SearchPage(driver, wait);
        Assert.assertTrue(searchPage.getResultCount() > 0,
                "No products found for MacBook");
    }

    @Test(priority = 2)
    public void testSearchNoResult() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.searchProduct("xyzabc123notexist");

        SearchPage searchPage = new SearchPage(driver, wait);
        String msg = searchPage.getNoResultMessage();

        System.out.println("No result message: [" + msg + "]");

        Assert.assertTrue(
                msg.contains("There is no product that matches the search criteria."),
                "No result message not shown. Actual: [" + msg + "]"
        );
    }

    @Test(priority = 3)
    public void testProductDetail() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.searchProduct("MacBook");

        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.clickFirstProduct();

        ProductPage productPage = new ProductPage(driver, wait);
        Assert.assertFalse(productPage.getProductName().isEmpty(),
                "Product name is empty");
        Assert.assertFalse(productPage.getProductPrice().isEmpty(),
                "Product price is empty");
    }
}