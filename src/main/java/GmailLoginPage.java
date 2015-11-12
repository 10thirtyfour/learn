import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by User on 12.11.2015.
 */
public class GmailLoginPage extends BasicPage {
    @FindBy(id = "Email")
    private WebElement usernameInput;

    @FindBy(id = "next")
    private WebElement nextButton;

    @FindBy(id = "Passwd")
    private WebElement passwordInput;

    @FindBy(id = "signIn")
    private WebElement signInButton;

    @FindBy(xpath = "//div[contains(@class,'logo logo-w')]")
    private WebElement logo;

    public GmailLoginPage(WebDriver driver) {
        super(driver);
    }

    public void inputUsername(String email) {
        usernameInput().type(email);
        nextButton().click();
    }

    public void inputPassword(String passwd) {
        passwordInput().type(passwd);
        signInButton().click();
    }

    public WebElementFacade signInButton() {
        return element(signInButton);
    }

    public WebElementFacade usernameInput() {
        return element(usernameInput);
    }

    public WebElementFacade nextButton() {
        return element(nextButton);
    }

    public WebElementFacade passwordInput() {
        return element(passwordInput);
    }

    public WebElementFacade logo() {
        return element(logo);
    }
}
