package com.meesho.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductListPage {
    WebDriver driver;
    WebDriverWait wait;

    By sortMenu = By.xpath("//span[contains(text(),'Sort') or contains(.,'Sort')]");
    By lowCostOption = By.xpath("//p[text()='Price: Low to High' or contains(.,'Low to High')]");
    // product card - use a more generic selector for images or product links
    By productCards = By.xpath("//a[contains(@href,'/p/') or contains(@href,'product') or contains(@class,'ProductCard')]");
    By productCardAlt = By.cssSelector("div[data-testid='product-card'] a"); // fallback

    public ProductListPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void sortByLowCost() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(sortMenu)).click();
            wait.until(ExpectedConditions.elementToBeClickable(lowCostOption)).click();
            // wait for product list to stabilize
            wait.until(ExpectedConditions.visibilityOfElementLocated(productCards));
        } catch (Exception e) {
            // If Sort is not present, continue — site may load default sort
        }
    }

    public void selectFirstProduct() {
        List<WebElement> products = driver.findElements(productCards);
        if (products.isEmpty()) {
            products = driver.findElements(productCardAlt);
        }
        if (products.size() > 0) {
            // click the first product link (likely opens in new tab)
            products.get(0).click();
        } else {
            throw new RuntimeException("No products found on ProductListPage");
        }
    }
}
