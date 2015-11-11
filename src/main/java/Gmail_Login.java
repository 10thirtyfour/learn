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

    public static void LocateElementbyXpath (WebDriver driver, String elementName, String elementPath)
    {
        logger.info("verifying "+elementName);

        try {
            driver.findElement(By.xpath(elementPath));
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error(elementName+" is not present", e);
        }

    }

    public static void LocateElementbyId (WebDriver driver, String elementName, String elementId)
    {
        logger.info("verifying "+elementName);

        try {
            driver.findElement(By.id(elementId));
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error(elementName+" is not present", e);
        }

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
        logger.info("Test has started...");
        String expectedTitle = "Sign in - Google Accounts";
        String actualTitle = driver.getTitle();
        Assert.assertEquals("Title is incorrect", expectedTitle, actualTitle);

        LocateElementbyXpath(driver,"Sign in message","//h2");

        LocateElementbyXpath(driver,"Logo","//div[contains(@class,'logo logo-w')]");

        LocateElementbyId(driver,"Canvas","canvas");

        LocateElementbyXpath(driver,"Need Help link","//input[@id='next']/following-sibling::a");

        LocateElementbyXpath(driver,"Create Account link","//span[@id='link-signup']/a");

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
            WebElement errorMessage = driver.findElement(By.xpath("//span[@id='errormsg_0_Passwd']"));
            String actualError = errorMessage.getText();
            String expectedError = "The email and password you entered don't match.";
            Assert.assertEquals("Error Message is incorrect", expectedError, actualError);

        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("Error Message is not present, e");
        }

        logger.info("verifying red border of password field");
        password = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("Passwd")));
        Assert.assertFalse(password.getAttribute("class").isEmpty());

        driver.close();
        logger.info("Test script executed successfully.");
        driver.quit();
    }
}

