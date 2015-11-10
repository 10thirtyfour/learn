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
        logger.info("Maximize browser window...");
        driver.manage().window().maximize();
        logger.info("Test has started...");
        String expectedTitle = "Sign in - Google Accounts  ";
        String actualTitle = driver.getTitle();
        Assert.assertEquals("Title is incorrect", expectedTitle, actualTitle);
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
        driver.close();
        logger.info("Test script executed successfully.");
        driver.quit();
    }
}

