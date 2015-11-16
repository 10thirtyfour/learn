import org.junit.rules.Timeout;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by artemk on 11/12/15.
 */
public class WebElementFacade implements WebElement {
    private static final long WAIT_TIMEOUT = 30000;
    protected WebElement webElement;
    protected final WebDriver driver;

    private WebElementFacade(WebElement webElement, WebDriver webDriver) {
        this.webElement = webElement;
        this.driver = webDriver;
    }

    public static WebElementFacade wrapWebElement(WebElement webElement, WebDriver webDriver) {
        return new WebElementFacade(webElement, webDriver);
    }

    public WebElementFacade type(final String value) {
        webElement.clear();
        webElement.sendKeys(value);
        return this;
    }

    public boolean isVisible() {
        try {
            return webElement.isDisplayed() && !webElement.getAttribute("class").contains("display: none");
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public void click() {
        webElement.click();
    }

    public boolean isCurrentlyVisible() {
        try {
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            return isVisible();
        } finally {
            driver.manage().timeouts()
                    .implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    public WebDriverWait waitForCondition() {
        return new WebDriverWait(driver, WAIT_TIMEOUT);
    }

    private ExpectedCondition<Boolean> elementIsNotDisplayed() {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return !isCurrentlyVisible();
            }
        };
    }

    protected ExpectedCondition<Boolean> elementIsDisplayed() {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return (webElement != null) && (webElement.isDisplayed());
                } catch (NullPointerException e) {
                    // Selenium sometimes throws a NPE if the element is not present at all on the page.
                    return false;
                }
            }
        };
    }

    public WebElementFacade waitUntilNotVisible() {
        waitForCondition().until(elementIsNotDisplayed());
        return this;
    }

    public WebElementFacade waitUntilVisible() {
        waitForCondition().until(elementIsDisplayed());
        return this;
    }

    @Override
    public void submit() {
        webElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        webElement.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        webElement.clear();
    }

    @Override
    public String getTagName() {
        return webElement.getTagName();
    }

    @Override
    public String getAttribute(String attributeName) {
        return webElement.getAttribute(attributeName);
    }

    @Override
    public boolean isSelected() {
        return webElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return webElement.isEnabled();
    }

    @Override
    public String getText() {
        return webElement.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return webElement.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return webElement.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return webElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        return webElement.getSize();
    }

    @Override
    public String getCssValue(String css) {
        return webElement.getCssValue(css);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> xOutputType) throws WebDriverException {
        return null;
    }
}
