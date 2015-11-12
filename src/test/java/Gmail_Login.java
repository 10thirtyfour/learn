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
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class Gmail_Login {
    protected static final Logger logger = Logger.getLogger(Gmail_Login.class);

    public static final String APP_URL = "https://accounts.google.com";

    @Test
    public void testGmailLogin() {
        // objects and variables instantiation
        logger.info("Running driver...");
        WebDriver driver = new FirefoxDriver();
      //  GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
        GmailLoginPage gmailLoginPage = PageFactory.initElements(driver,GmailLoginPage.class);
        logger.info("Launch browser...");
        gmailLoginPage.open(APP_URL);
        logger.info("Maximize browser window...");
        driver.manage().window().maximize();
        logger.info("Set implicity wait...");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        logger.info("Test has started...");
        try {

            gmailLoginPage.titleCheck();
          //  String expectedTitle = "Sign in - Google Accounts";
          //  String actualTitle = driver.getTitle();
           // Assert.assertEquals("Title is incorrect", expectedTitle, actualTitle);



            DriverUtils.assertElementVisible("Logo", driver, "//div[contains(@class,'logo logo-w')]");
            DriverUtils.assertElementVisible("Canvas", driver, "canvas");
            DriverUtils.assertElementVisible("Need Help link", driver, "//input[@id='next']/following-sibling::a");
            DriverUtils.assertElementVisible("Create Account link", driver, "//span[@id='link-signup']/a");
            DriverUtils.assertElementVisible("Next button", gmailLoginPage.nextButton());


            DriverUtils.assertElementVisible("Email input", driver, "Email");
            gmailLoginPage.inputUsername("SeleniumTest");


          //  WebElement username = driver.findElement(By.id("Email"));
           // username.clear();
           // username.sendKeys("TestSelenium");


           // WebElement NextButton = driver.findElement(By.id("next"));
          //  NextButton.click();

            DriverUtils.assertElementVisible("Sign in button", gmailLoginPage.signInButton());
            gmailLoginPage.inputPassword("password");

          //  DriverUtils.assertElementVisible("Password button", driver, "Passwd");
          //  WebElement password = driver.findElement(By.id("Passwd"));
          //  password.clear();
          //  password.sendKeys("password123");


         //   WebElement SignInButton = driver.findElement(By.id("signIn"));
          //  SignInButton.click();

            logger.info("verifying Error Message");

            DriverUtils.assertElementVisible("Error message", driver, "//span[@id='errormsg_0_Passwd']");
            WebElement errorMessage = driver.findElement(By.xpath("//span[@id='errormsg_0_Passwd']"));
            String actualError = errorMessage.getText();
            String expectedError = "The email and password you entered don't match.";
            Assert.assertEquals("Error Message is incorrect", expectedError, actualError);

            logger.info("verifying red border of password field");

          //  DriverUtils.assertElementVisible("Password button", driver, "Passwd");
          //  password = driver.findElement(By.id("Passwd"));
          //  Assert.assertFalse(password.getAttribute("class").isEmpty());

        } finally {
            logger.info("Shutting down driver...");
            gmailLoginPage.close();
            driver.quit();
        }
        logger.info("Test script executed successfully.");
    }
}

