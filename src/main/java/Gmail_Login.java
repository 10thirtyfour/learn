/**
 * Created by User on 06.11.2015.
 */


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Gmail_Login {
    protected static final Logger logger = Logger.getLogger(Gmail_Login.class);

    public static final String APP_URL = "https://accounts.google.com";

    /**
     8
     * @param args
    9
     */
    public static void main(String[] args) {
        // objects and variables instantiation
        logger.info("Running driver...");
        WebDriver driver = new FirefoxDriver();
        logger.info("Launch browser...");
        driver.get(APP_URL);
   //     logger.info("Maximize browser window...");
    //    driver.manage().window().maximize();
        logger.info("Test has started...");
        String expectedTitle = "Sign in - Google Accounts";
        String actualTitle = driver.getTitle();
          Assert.assertEquals("Title is incorrect", expectedTitle, actualTitle);
        logger.info("verifying Logo");
        try {
            WebElement logo = driver.findElement(By.xpath("//*[contains(@class,'logo logo-w')]"));
       }
        catch (org.openqa.selenium.NoSuchElementException e)
        {
           logger.info("Logo is not present");
        }

        logger.info("verifying Sign in message");

        try {
            WebElement signIn = driver.findElement(By.xpath("//*[contains(text(),'Sign in with your Google Account')]"));
        }
        catch (org.openqa.selenium.NoSuchElementException e)
        {
            logger.info("Sign in message is not present");
        }

        logger.info("verifying Canvas");

        try {
            WebElement canvas = driver.findElement(By.id("canvas"));
        }
        catch (org.openqa.selenium.NoSuchElementException e)
        {
            logger.info("Canvas is not present");
        }

        logger.info("verifying Need Help link");

        try {
            WebElement needHelp = driver.findElement(By.xpath("//*[contains(@class,'need-help')]"));
        }
        catch (org.openqa.selenium.NoSuchElementException e)
        {
            logger.info("Need Help link is not present");
        }

        logger.info("verifying Create Account link");

        try {
            WebElement needHelp = driver.findElement(By.xpath("//*[contains(text(),'Create account')]"));
        }
        catch (org.openqa.selenium.NoSuchElementException e)
        {
            logger.info("Create Account link is not present");
        }

        WebElement username = driver.findElement(By.id("Email"));
        username.clear();
        username.sendKeys("TestSelenium");
        WebElement NextButton = driver.findElement(By.id("next"));
        NextButton.click();
        WebElement password = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("Passwd")));
        password.clear();
        password.sendKeys("password123");
        WebElement SignInButton = driver.findElement(By.id("signIn"));
        SignInButton.click();

        logger.info("verifying Error Message");

        try {
            WebElement errorMessage = driver.findElement(By.xpath("//*[contains(@id,'errormsg_0_Passwd')]"));
           String errorMessageActualText = errorMessage.getText();
           String errorMessageExpectedText = "The email and password you entered don't match.";
            Assert.assertEquals("Error Message is incorrect", errorMessageExpectedText, errorMessageActualText);

        }
        catch (org.openqa.selenium.NoSuchElementException e)
        {
            logger.info("Error Message is not present");
        }

        driver.close();
        logger.info("Test script executed successfully.");
        driver.quit();
    }
}

