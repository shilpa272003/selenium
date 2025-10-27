package com.meesho.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductDetailPage {
    WebDriver driver;
    WebDriverWait wait;

    By addToCartBtn = By.xpath("//span[contains(text(),'Add to Cart') or contains(text(),'ADD TO CART') or //button[contains(.,'Add to Cart')]]");

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
    }
}
