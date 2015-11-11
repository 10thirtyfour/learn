/**
 * Created by User on 06.11.2015.
 */


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Gmail_Login {
    protected static final Logger logger = Logger.getLogger(Gmail_Login.class);

    public static final String APP_URL = "https://accounts.google.com";

    @Test
    public void testGmailLogin() {
        // objects and variables instantiation
        logger.info("Running driver...");
        WebDriver driver = new FirefoxDriver();
        logger.info("Launch browser...");
        driver.get(APP_URL);
        logger.info("Maximize browser window...");
        driver.manage().window().maximize();
        logger.info("Set implicity wait...");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        logger.info("Test has started...");
        try {
            String expectedTitle = "Sign in - Google Accounts";
            String actualTitle = driver.getTitle();
            Assert.assertEquals("Title is incorrect", expectedTitle, actualTitle);

            DriverUtils.assertElementVisible("Logo", driver, "//div[contains(@class,'logo logo-w')]");
            DriverUtils.assertElementVisible("Canvas", driver, "canvas");
            DriverUtils.assertElementVisible("Need Help link", driver, "//input[@id='next']/following-sibling::a");
            DriverUtils.assertElementVisible("Create Account link", driver, "//span[@id='link-signup']/a");

            DriverUtils.assertElementVisible("Email input", driver, "Email");
            WebElement username = driver.findElement(By.id("Email"));
            username.clear();
            username.sendKeys("TestSelenium");

            DriverUtils.assertElementVisible("Next button", driver, "next");
            WebElement NextButton = driver.findElement(By.id("next"));
            NextButton.click();

            DriverUtils.assertElementVisible("Password button", driver, "Passwd");
            WebElement password = driver.findElement(By.id("Passwd"));
            password.clear();
            password.sendKeys("password123");

            DriverUtils.assertElementVisible("Sign in button", driver, "signIn");
            WebElement SignInButton = driver.findElement(By.id("signIn"));
            SignInButton.click();

            logger.info("verifying Error Message");

            DriverUtils.assertElementVisible("Error message", driver, "//span[@id='errormsg_0_Passwd']");
            WebElement errorMessage = driver.findElement(By.xpath("//span[@id='errormsg_0_Passwd']"));
            String actualError = errorMessage.getText();
            String expectedError = "The email and password you entered don't match.";
            Assert.assertEquals("Error Message is incorrect", expectedError, actualError);

            logger.info("verifying red border of password field");

            DriverUtils.assertElementVisible("Password button", driver, "Passwd");
            password = driver.findElement(By.id("Passwd"));
            Assert.assertFalse(password.getAttribute("class").isEmpty());

        } finally {
            logger.info("Shutting down driver...");
            driver.close();
            driver.quit();
        }
        logger.info("Test script executed successfully.");
    }
}

