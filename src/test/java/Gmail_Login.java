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

    public static final String EXPEXTED_TITLE = "Sign in - Google Accounts";

    public static final String APP_URL = "https://accounts.google.com";

    public static final String IS_NOT_VISIBLE = " is not visible";

    public static final String EXPECTED_ERROR = "The email and password you entered don't match.";

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

            Assert.assertEquals("Title is incorrect", EXPEXTED_TITLE, gmailLoginPage.getPageTitle());

            Assert.assertTrue("Logo" + IS_NOT_VISIBLE, gmailLoginPage.logo().isVisible());
            Assert.assertTrue("Canvas" + IS_NOT_VISIBLE, gmailLoginPage.canvas().isVisible());
            Assert.assertTrue("Need Help link" + IS_NOT_VISIBLE, gmailLoginPage.needHelpLink().isVisible());
            Assert.assertTrue("Create Account link" + IS_NOT_VISIBLE, gmailLoginPage.createAccountLink().isVisible());
            Assert.assertTrue("Next button" + IS_NOT_VISIBLE, gmailLoginPage.nextButton().isVisible());

            Assert.assertTrue("User name" + IS_NOT_VISIBLE, gmailLoginPage.usernameInput().isVisible());
            gmailLoginPage.inputUsername("SeleniumTest");

            Assert.assertTrue("Password" + IS_NOT_VISIBLE, gmailLoginPage.passwordInput().isVisible());
            gmailLoginPage.inputPassword("password");

            logger.info("verifying Error Message");

            Assert.assertTrue("Error message" + IS_NOT_VISIBLE, gmailLoginPage.errorMessage().isVisible());
            Assert.assertEquals("Error Message is incorrect", EXPECTED_ERROR, gmailLoginPage.errorMessage().getText());

            logger.info("verifying red border of password field");

            Assert.assertFalse(gmailLoginPage.passwordInput().getAttribute("class").isEmpty());

        } finally {
            logger.info("Shutting down driver...");
            driver.close();
            driver.quit();
        }
        logger.info("Test script executed successfully.");
    }
}

