package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    // Locators
    private final By accountHeading = By.xpath("//h2[text()='My Account']");
    private final By logoutLink = By.linkText("Logout");
    private final By myAccountDropdown = By.xpath("//a[@title='My Account']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isUserLoggedIn() {
        return isElementDisplayed(accountHeading);
    }

    public void logout() {
        click(myAccountDropdown);
        click(logoutLink);
    }

    public String getAccountHeadingText() {
        return getText(accountHeading);
    }
}