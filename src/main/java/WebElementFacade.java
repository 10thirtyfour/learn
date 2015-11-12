import org.openqa.selenium.*;

import java.util.List;

/**
 * Created by artemk on 11/12/15.
 */
public class WebElementFacade implements WebElement {
    protected WebElement webElement;

    private WebElementFacade(WebElement webElement) {
        this.webElement = webElement;
    }

    public static WebElementFacade wrapWebElement(WebElement webElement) {
        return new WebElementFacade(webElement);
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
