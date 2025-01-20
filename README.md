---

# Retail Web Application Automation Testing

This project is designed to automate the testing of a retail web application using **Selenium WebDriver** and **Cucumber** (BDD framework). It simulates different user actions like login and error handling and is structured in a way that adheres to Behavior Driven Development (BDD) principles.

## Table of Contents
- [Project Setup](#project-setup)
- [Dependencies](#dependencies)
- [Folder Structure](#folder-structure)
- [Feature Files](#feature-files)
- [Step Definitions](#step-definitions)
- [Test Runner](#test-runner)
- [How to Run the Tests](#how-to-run-the-tests)
- [Contributing](#contributing)
- [License](#license)

---

## Project Setup

This project is a Maven-based Java application that uses **Selenium** for browser automation and **Cucumber** for Behavior Driven Development (BDD). To get started, follow these steps:

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/yourusername/retail-automation.git
    ```

2. **Import the Project into Your IDE:**
   - For **Eclipse**, choose `File -> Import -> Existing Maven Projects`.
   - For **IntelliJ IDEA**, choose `File -> Open` and select the project folder.

3. **Install Dependencies:**
   Maven will automatically download the necessary dependencies listed in `pom.xml`. If Maven does not download automatically, run the following command to install them manually:
   
   ```bash
   mvn install
   ```

---

## Dependencies

The project uses the following key dependencies:

- **Selenium WebDriver:** Used for browser automation to simulate user interactions.
- **Cucumber:** A BDD framework for writing test cases in Gherkin language (feature files).
- **JUnit:** Used to run Cucumber tests.
- **WebDriverManager:** Automatically handles browser drivers like ChromeDriver, FirefoxDriver, etc.

These dependencies are listed in the `pom.xml` file.

---

## Folder Structure

Here's a brief overview of the project folder structure:

```
retail-automation/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── retail/
│   │               └── utils/  # Utility classes for WebDriver, etc.
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── retail/
│       │           ├── runners/     # Test runner class
│       │           └── steps/       # Step definition classes
│       └── resources/
│           └── login.feature        # Cucumber feature files
├── pom.xml
└── README.md
```

---

## Feature Files

The **feature files** describe the behavior of the system in Gherkin syntax. They are located in the `src/test/resources` folder.

Example of a **login.feature** file:

```gherkin
Feature: Login to the Retail Web Application

  Scenario: User successfully logs in
    Given User is on the login page
    When User enters valid credentials
    Then User should be redirected to the dashboard

  Scenario: User fails to log in with incorrect credentials
    Given User is on the login page
    When User enters invalid credentials
    Then User should see an error message
```

---

## Step Definitions

Step definition files contain the actual Java methods that map to the steps in your feature files. These are located in the `src/test/java/com/retail/steps` folder.

Example of `LoginSteps.java` step definitions:

```java
package com.retail.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class LoginSteps {

    WebDriver driver;

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        driver = new ChromeDriver();
        driver.get("https://www.retailwebsite.com/login");
    }

    @When("User enters valid credentials")
    public void userEntersValidCredentials() {
        driver.findElement(By.id("username")).sendKeys("validUser");
        driver.findElement(By.id("password")).sendKeys("validPassword");
        driver.findElement(By.id("loginButton")).click();
    }

    @Then("User should be redirected to the dashboard")
    public void userIsRedirectedToDashboard() {
        assertTrue(driver.getCurrentUrl().contains("dashboard"));
        driver.quit();
    }

    @When("User enters invalid credentials")
    public void userEntersInvalidCredentials() {
        driver.findElement(By.id("username")).sendKeys("invalidUser");
        driver.findElement(By.id("password")).sendKeys("invalidPassword");
        driver.findElement(By.id("loginButton")).click();
    }

    @Then("User should see an error message")
    public void userSeesErrorMessage() {
        assertTrue(driver.findElement(By.id("errorMessage")).isDisplayed());
        driver.quit();
    }
}
```

---

## Test Runner

The **Test Runner** is responsible for running the Cucumber tests. It is a JUnit test class that uses Cucumber annotations to hook into the feature files and step definitions.

Example of `TestRunner.java`:

```java
package com.retail.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
  features = "src/test/resources",     # Location of feature files
  glue = "com.retail.steps",           # Package for step definition files
  plugin = {"pretty", "html:target/cucumber-reports"}  # Report format
)
public class TestRunner {
}
```

---

## How to Run the Tests

1. **Run Tests from Maven:**

   To run the tests using Maven, use the following command:

   ```bash
   mvn test
   ```

   This will execute all the Cucumber tests and generate a report in the `target/cucumber-reports` folder.

2. **Run Tests from IDE (e.g., IntelliJ or Eclipse):**

   - Right-click on the `TestRunner` class.
   - Select `Run` to execute the tests directly from your IDE.

---

## Contributing

We welcome contributions to the project. To contribute:

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a Pull Request.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
