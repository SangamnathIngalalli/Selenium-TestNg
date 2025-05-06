package com.opencart.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.opencart.config.ConfigReader;
import com.opencart.pages.DashboardPage;
import com.opencart.pages.LoginPage;
import com.opencart.utils.WebDriverManager;
import com.opencart.utils.TestListener;

@Listeners(TestListener.class)
@Feature("Login Functionality")
public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        driver = WebDriverManager.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify user can login with valid credentials")
    @Story("Valid Login")
    public void testSuccessfulLogin() {
        // Get credentials from config
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");
        
        // Login
        DashboardPage dashboardPage = loginPage.login(username, password);
        
        // Verify successful login
        Assert.assertTrue(dashboardPage.isUserLoggedIn(), "User should be logged in successfully");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify system shows error message with invalid credentials")
    @Story("Invalid Login")
    public void testLoginWithInvalidCredentials() {
        // Login with invalid credentials
        loginPage.login("invalid@example.com", "wrongpassword");
        
        // Verify warning message is displayed
        Assert.assertTrue(loginPage.isWarningMessageDisplayed(), "Warning message should be displayed");
    }

    @AfterMethod
    public void tearDown() {
        WebDriverManager.quitDriver();
    }
}