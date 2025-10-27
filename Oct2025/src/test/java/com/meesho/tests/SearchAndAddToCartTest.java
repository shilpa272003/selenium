package com.meesho.tests;

import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.meesho.base.BaseTest;
import com.meesho.pages.*;

import java.util.ArrayList;

public class SearchAndAddToCartTest extends BaseTest {

    @Test
    public void searchSortAddToCart() throws InterruptedException {
        // create extent test and attach to BaseTest.test for screenshot support
        this.test = extent.createTest("Search → Sort → Add to Cart → View Cart");
        test.info("Starting test");

        navigateUrl("https://www.meesho.com/");
        test.info("Opened Meesho.com");

        HomePage home = new HomePage(driver);
        home.searchFor("saree");
        test.info("Searched for 'saree'");

        ProductListPage listPage = new ProductListPage(driver);
        listPage.sortByLowCost();
        test.info("Sorted items by low cost (if available)");

        listPage.selectFirstProduct();
        test.info("Clicked first product (may open in new tab)");

        // handle new tab if opened
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(tabs.size() - 1)); // switch to last opened tab
            test.info("Switched to product detail tab");
        }

        ProductDetailPage detailPage = new ProductDetailPage(driver);
        detailPage.addToCart();
        test.info("Clicked Add to Cart");

        // sometimes clicking add-to-cart triggers mini-cart or view-cart link
        CartPage cart = new CartPage(driver);
        cart.viewCart();
        test.info("Clicked View Cart");

        if (cart.isCartDisplayed()) {
            test.pass("Cart page displayed successfully");
        } else {
            test.fail("Cart page not displayed");
        }
    }
}
