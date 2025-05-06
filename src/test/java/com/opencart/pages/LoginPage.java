package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.config.ConfigReader;

public class LoginPage extends BasePage {
    // Locators
    private final By emailField = By.id("input-email");
    private final By passwordField = By.id("input-password");
    private final By loginButton = By.xpath("//input[@value='Login']");
    private final By warningMessage = By.cssSelector(".alert-danger");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLoginPage() {
        driver.get(ConfigReader.getProperty("url") + "index.php?route=account/login");
    }

    public DashboardPage login(String email, String password) {
        sendKeys(emailField, email);
        sendKeys(passwordField, password);
        click(loginButton);
        return new DashboardPage(driver);
    }

    public DashboardPage loginWithDefaultCredentials() {
        return login(
            ConfigReader.getProperty("username"),
            ConfigReader.getProperty("password")
        );
    }

    public boolean isWarningMessageDisplayed() {
        return isElementDisplayed(warningMessage);
    }

    public String getWarningMessageText() {
        return getText(warningMessage);
    }
}