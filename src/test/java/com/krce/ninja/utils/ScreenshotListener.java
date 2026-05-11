package com.krce.ninja.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentReportManager.getInstance()
                .createTest(result.getName());

        ExtentReportManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = ExtentReportManager.getTest();
        test.log(Status.FAIL, result.getThrowable());

        try {
            System.out.println("Inside failure block");

            WebDriver driver = (WebDriver) result.getTestContext()
                    .getAttribute("driver");

            System.out.println("Driver = " + driver);

            if (driver == null) {
                System.out.println("Driver is NULL → Screenshot skipped");
                return;
            }

            Files.createDirectories(Paths.get("screenshots"));

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(new Date());

            String fileName = result.getName() + "_" + timestamp + ".png";
            String path = "screenshots/" + fileName;

            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Files.copy(src.toPath(), Paths.get(path));

            System.out.println(" Screenshot saved at: " + path);

            test.addScreenCaptureFromPath(path);

        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flush();
    }
}