package com.meesho.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    // This placeholder might change — alternative: use input[type='search'] if available
    By searchBox = By.xpath("//input[@placeholder='Try Saree, Kurti or Search by Product Code' or @placeholder[contains(.,'Search')]]");
    By locationPopupClose = By.xpath("//button[contains(@aria-label,'close') or contains(text(),'Not now') or contains(text(),'Later')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Close typical popups if shown (non-blocking)
    public void safeClosePopupIfPresent() {
        try {
            if (driver.findElements(locationPopupClose).size() > 0) {
                wait.until(ExpectedConditions.elementToBeClickable(locationPopupClose)).click();
            }
        } catch (Exception e) {
            // ignore - popup not present
        }
    }

    public void searchFor(String productName) {
        safeClosePopupIfPresent();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys(productName + Keys.ENTER);
    }
}
