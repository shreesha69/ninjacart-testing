package com.krce.ninja.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter(
                    "reports/NinjaShopReport.html"
            );
            reporter.config().setDocumentTitle("NinjaShop Report");
            reporter.config().setReportName("Automation Results");
            reporter.config().setTheme(Theme.DARK);
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester",      "Shree");
            extent.setSystemInfo("Application", "TutorialsNinja");
        }
        return extent;
    }

    public static void setTest(ExtentTest extentTest) { test.set(extentTest); }
    public static ExtentTest getTest()                { return test.get(); }
    public static void flush()                        { if (extent != null) extent.flush(); }
}