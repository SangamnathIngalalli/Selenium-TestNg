package com.opencart.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // Not implemented
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Not implemented
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Get the driver instance from the test class
        Object testClass = result.getInstance();
        WebDriver driver = null;
        
        // Use reflection to get the driver field
        try {
            driver = (WebDriver) testClass.getClass().getDeclaredField("driver").get(testClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Take screenshot if driver is available
        if (driver != null) {
            saveScreenshot(driver);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Not implemented
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not implemented
    }

    @Override
    public void onStart(ITestContext context) {
        // Not implemented
    }

    @Override
    public void onFinish(ITestContext context) {
        // Not implemented
    }
    
    @Attachment(value = "Page Screenshot", type = "image/png")
    private byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}