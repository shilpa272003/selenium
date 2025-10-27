package com.meesho.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;
    static String projectPath = System.getProperty("user.dir");

    public static ExtentReports getInstance() {
        if (extent == null) {
            try {
                String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
                String reportDir = projectPath + "/src/test/resources/Reports/";
                new File(reportDir).mkdirs();
                String reportPath = reportDir + "MeeshoReport_" + date + ".html";
                ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
                extent = new ExtentReports();
                extent.attachReporter(spark);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return extent;
    }
}
