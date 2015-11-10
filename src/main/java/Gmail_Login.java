/**
 * Created by User on 06.11.2015.
 */


import org.apache.log4j.Logger;
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
        // launch the firefox browser and open the application url
        driver.get(APP_URL);

        logger.info("Maximize browser window...");
        // maximize the browser window
        driver.manage().window().maximize();
        // declare and initialize the variable to store the expected title of the webpage.

        logger.info("Test has started...");
        String expectedTitle = " Sign in - Google Accounts ";
        // fetch the title of the web page and save it into a string variable
        String actualTitle = driver.getTitle();
        // compare the expected title of the page with the actual title of the page and print the result
        if (expectedTitle.equals(actualTitle))
        {
            logger.info("Verification Successful - The correct title is displayed on the web page.");
        }
        else
        {
            logger.info("Verification Failed - An incorrect title is displayed on the web page.");
        }

        // enter a valid username in the email textbox
        WebElement username = driver.findElement(By.id("Email"));
        username.clear();
        username.sendKeys("TestSelenium");
        // enter a valid password in the password textbox
        WebElement NextButton = driver.findElement(By.id("next"));
        NextButton.click();

        //yours implementation
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        WebElement password = driver.findElement(By.id("Passwd"));

        //alternative way
        WebElement password = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("Passwd")));

        password.clear();

        password.sendKeys("password123");
      //   click on the Sign in button
        WebElement SignInButton = driver.findElement(By.id("signIn"));
        SignInButton.click();
        // close the web browser
        driver.close();
        logger.info("Test script executed successfully.");
        // terminate the program
        driver.quit();
    }
}