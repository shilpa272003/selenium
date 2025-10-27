package com.meesho.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    By viewCartBtn = By.xpath("//a[contains(@href,'cart') or contains(text(),'View Cart') or contains(.,'Cart')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void viewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartBtn)).click();
    }

    public boolean isCartDisplayed() {
        return driver.getCurrentUrl().contains("cart");
    }
}
