/**
 * Created by User on 06.11.2015.
 */


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Gmail_Login {

    public static final String APP_URL = "https://accounts.google.com";

    /**
     8
     * @param args
    9
     */
    public static void main(String[] args) {
        // objects and variables instantiation
        WebDriver driver = new FirefoxDriver();

        // launch the firefox browser and open the application url
        driver.get(APP_URL);

        // maximize the browser window
        driver.manage().window().maximize();
        // declare and initialize the variable to store the expected title of the webpage.
        String expectedTitle = " Sign in - Google Accounts ";
        // fetch the title of the web page and save it into a string variable
        String actualTitle = driver.getTitle();
        // compare the expected title of the page with the actual title of the page and print the result
        if (expectedTitle.equals(actualTitle))
        {
            System.out.println("Verification Successful - The correct title is displayed on the web page.");
        }
        else
        {
            System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
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
                .until(ExpectedConditions.presenceOfElementLocated(By.id("Passfswd")));

        password.clear();
        password.sendKeys("password123");
      //   click on the Sign in button
        WebElement SignInButton = driver.findElement(By.id("signIn"));
        SignInButton.click();
        // close the web browser
        driver.close();
        System.out.println("Test script executed successfully.");
        // terminate the program
        System.exit(0);
    }
}