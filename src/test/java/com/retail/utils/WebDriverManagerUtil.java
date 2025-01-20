package com.retail.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverManagerUtil {

    // Method to initialize ChromeDriver
    public static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();  // This will automatically download the ChromeDriver
        return new ChromeDriver();  // Returns an instance of ChromeDriver
    }

    // Method to initialize FirefoxDriver
    public static WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();  // This will automatically download the FirefoxDriver
        return new FirefoxDriver();  // Returns an instance of FirefoxDriver
    }

    // Method to initialize EdgeDriver
    public static WebDriver getEdgeDriver() {
        WebDriverManager.edgedriver().setup();  // This will automatically download the EdgeDriver
        return new EdgeDriver();  // Returns an instance of EdgeDriver
    }

    // General method to initialize any WebDriver based on browser type
    public static WebDriver getDriver(String browserType) {
        WebDriver driver;

        switch (browserType.toLowerCase()) {
            case "chrome":
                driver = getChromeDriver();  // Initialize ChromeDriver
                break;
            case "firefox":
                driver = getFirefoxDriver();  // Initialize FirefoxDriver
                break;
            case "edge":
                driver = getEdgeDriver();  // Initialize EdgeDriver
                break;
            default:
                throw new IllegalArgumentException("Browser type not supported: " + browserType);
        }
        return driver;  // Return the WebDriver instance based on the specified browser type
    }
}
