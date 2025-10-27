package com.meesho.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String projectPath = System.getProperty("user.dir");
        String screenshotPath = projectPath + "/src/test/resources/Screenshots/" + screenshotName + "_" + date + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File dest = new File(screenshotPath);
            dest.getParentFile().mkdirs(); // ensure folder exists
            FileUtils.copyFile(src, dest);
            return screenshotPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
