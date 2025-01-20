package com.retail.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import com.retail.pages.LoginPage;
import static org.junit.Assert.assertTrue;

public class LoginSteps {
    WebDriver driver = WebDriverManager.chromedriver().create();
    LoginPage loginPage = new LoginPage(driver);

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        driver.get("https://www.retailwebsite.com/login");
    }

    @When("User enters valid credentials")
    public void userEntersValidCredentials() {
        loginPage.enterUsername("validUser");
        loginPage.enterPassword("validPassword");
        loginPage.clickLogin();
    }

    @Then("User should be redirected to the dashboard")
    public void userIsRedirectedToDashboard() {
        assertTrue(driver.getCurrentUrl().contains("dashboard"));
        driver.quit();
    }
}
