package com.retail.tests;

import com.retail.pages.LoginPage;
import com.retail.utils.WebDriverManagerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        // Initialize the WebDriver (e.g., Chrome in this case)
        driver = WebDriverManagerUtil.getDriver("chrome");  // You can change this to "firefox", "edge", etc.
        loginPage = new LoginPage(driver);  // Initialize the LoginPage object
        driver.get("https://www.retailwebsite.com/login");  // Navigate to the login page
    }

    @Test(priority = 1)
    public void testSuccessfulLogin() {
        // Test case for valid login
        loginPage.enterUsername("validUser");  // Enter valid username
        loginPage.enterPassword("validPassword");  // Enter valid password
        loginPage.clickLogin();  // Click on the login button

        // Assert that the user is redirected to the dashboard after successful login
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard"), "User was not redirected to the dashboard");
    }

    @Test(priority = 2)
    public void testUnsuccessfulLogin() {
        // Test case for invalid login (wrong credentials)
        loginPage.enterUsername("invalidUser");  // Enter invalid username
        loginPage.enterPassword("invalidPassword");  // Enter invalid password
        loginPage.clickLogin();  // Click on the login button

        // Assert that an error message is displayed on invalid login
        boolean errorMessageDisplayed = driver.findElement(By.id("errorMessage")).isDisplayed();
        Assert.assertTrue(errorMessageDisplayed, "Error message was not displayed");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Close the browser after the test
        }
    }
}
