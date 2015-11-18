import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by artemk on 11/12/15.
 */
public class BasicPage {
    protected WebDriver driver;
    protected static final String DOMAIN = "http://character-gen.herokuapp.com";

    @FindBy(xpath = "//a[@href='/view/race_manager.html']")
    private WebElement racePageLink;

    @FindBy(xpath = "//h4")
    private WebElement successCreationLabel;

    public WebElementFacade element(WebElement webElement) {
        return WebElementFacade.wrapWebElement(webElement, driver);
    }

    public BasicPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void open(String url) {
        driver.get(url);
    }

    public WebElementFacade racePageLink() {
        return element(racePageLink);
    }

    public WebElementFacade successCreationLabel() {
        return element(successCreationLabel);
    }

}
