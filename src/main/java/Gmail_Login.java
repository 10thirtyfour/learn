/**
 * Created by User on 06.11.2015.
 */


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Gmail_Login {
    protected static final Logger logger = Logger.getLogger(Gmail_Login.class);

    public static final String APP_URL = "https://accounts.google.com";
    public static final String NOT_VISIBLE_TEXT = " is not visible";

    public static boolean isElementVisible(WebDriver driver, String locator) {
        try {
            WebElement element;
            if (locator.contains("//")) {
                element = driver.findElement(By.xpath(locator));
            } else {
                element = driver.findElement(By.id(locator));
            }
            return element.isDisplayed() && !element.getAttribute("class").contains("display: none");
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void assertElementVisible(String elementName, WebDriver driver, String locator) {
        Assert.assertTrue(elementName + NOT_VISIBLE_TEXT, isElementVisible(driver, locator));
    }
 // comment
    /**
     * 8
     *
     * @param args 9
     */
    public static void main(String[] args) {
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

            assertElementVisible("Logo", driver, "//div[contains(@class,'logo logo-w')]");
            assertElementVisible("Canvas", driver, "canvas");
            assertElementVisible("Need Help link", driver, "//input[@id='next']/following-sibling::a");
            assertElementVisible("Create Account link", driver, "//span[@id='link-signup']/a");

            assertElementVisible("Email input", driver, "Email");
            WebElement username = driver.findElement(By.id("Email"));
            username.clear();
            username.sendKeys("TestSelenium");

            assertElementVisible("Next button", driver, "next");
            WebElement NextButton = driver.findElement(By.id("next"));
            NextButton.click();

            assertElementVisible("Password button", driver, "Passwd");
            WebElement password = driver.findElement(By.id("Passwd"));
            password.clear();
            password.sendKeys("password123");

            assertElementVisible("Sign in button", driver, "signIn");
            WebElement SignInButton = driver.findElement(By.id("signIn"));
            SignInButton.click();

            logger.info("verifying Error Message");

            assertElementVisible("Error message", driver, "//span[@id='errormsg_0_Passwd']");
            WebElement errorMessage = driver.findElement(By.xpath("//span[@id='errormsg_0_Passwd']"));
            String actualError = errorMessage.getText();
            String expectedError = "The email and password you entered don't match.";
            Assert.assertEquals("Error Message is incorrect", expectedError, actualError);

            logger.info("verifying red border of password field");

            assertElementVisible("Password button", driver, "Passwd");
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

