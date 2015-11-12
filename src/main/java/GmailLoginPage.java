import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by User on 12.11.2015.
 */
public class GmailLoginPage {

    protected WebDriver driver;
    @FindBy (id="Email") WebElement username;
    @FindBy (id="next") WebElement nextButton;
    @FindBy (id="Passwd") WebElement password;
    @FindBy (id="signIn") WebElement signInButton;
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
        username.clear();
        username.sendKeys(email);
        nextButton.click();
    }

    public void inputPassword (String passwd)
    {
        password.clear();
        password.sendKeys(passwd);
        signInButton.click();
    }











}
