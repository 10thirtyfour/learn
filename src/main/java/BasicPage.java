import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by artemk on 11/12/15.
 */
public class BasicPage {
    protected WebDriver driver;

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

}
