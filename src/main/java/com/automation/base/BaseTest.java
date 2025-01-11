package com.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;
import java.util.Properties;
import java.io.FileInputStream;

/**
 * BaseTest class provides the setup and teardown functionality for Selenium WebDriver tests.
 */
public class BaseTest {

    protected WebDriver driver;
    protected Properties config;

    /**
     * Sets up the WebDriver for the specified browser and loads the configuration properties.
     *
     * @param browser the browser to use (e.g., "chrome" or "firefox").
     * @throws Exception if there is an error during setup.
     */
    public void setup(String browser) throws Exception {
        // Load configuration properties
        config = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        config.load(fis);

        // Browser setup
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        }

        driver.manage().timeouts().implicitlyWait( Duration.ofSeconds( 10 ) );
        driver.manage().window().maximize();
        driver.get( config.getProperty( "url" ) );
    }

    /**
     * Tears down the WebDriver instance.
     */
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
