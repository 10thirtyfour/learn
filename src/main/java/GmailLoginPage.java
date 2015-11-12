import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by User on 12.11.2015.
 */
public class GmailLoginPage {
    @FindBy (id="Email")
    WebElement usernameInput;

    @FindBy (id="next")
    WebElement nextButton;

    @FindBy (id="Passwd")
    WebElement passwordInput;

    @FindBy (id="signIn")
    WebElement signInButton;

    protected WebDriver driver;
    public static final String EXPEXTED_TITLE = "Sign in - Google Accounts";

    public GmailLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public GmailLoginPage() {

    }

    public void open(String url) {
        driver.get(url);
    }
    public void close() {
        driver.quit();
    }
    public String getTitle() {
        return driver.getTitle();
    }

    public void titleCheck ()
    {
        Assert.assertEquals("Title is incorrect", EXPEXTED_TITLE, getTitle());
    }

    public void inputUsername (String email)
    {
        usernameInput.clear();
        usernameInput.sendKeys(email);
        nextButton.click();
    }

    public void inputPassword (String passwd)
    {
        passwordInput.clear();
        passwordInput.sendKeys(passwd);
        signInButton.click();
    }

    public WebElement signInButton() {
        return signInButton;
    }

    public WebElement usernameInput() {
        return usernameInput;
    }

    public WebElement nextButton() {
        return nextButton;
    }

    public WebElement password() {
        return passwordInput;
    }











}
