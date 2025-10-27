package com.meesho.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.meesho.utilities.ExtentManager;
import com.meesho.utilities.ScreenshotUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.MediaEntityBuilder;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;   // ✅ Add this line

    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getInstance();
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
    //	WebDriverManager.edgedriver().setup();
    	//driver=new EdgeDriver();
    	
    	driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            try {
                if (result.getStatus() == ITestResult.FAILURE) {
                    String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
                    test.fail("Test Failed: " + result.getThrowable(),
                              MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                } else if (result.getStatus() == ITestResult.SUCCESS) {
                    test.pass("Test Passed Successfully!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                driver.quit();
            }
        }
    }

    public void navigateUrl(String url) {
        driver.get(url);
    }
}
