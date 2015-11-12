import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by artemk on 11/11/15.
 */
public class DriverUtils {
    public static final String NOT_VISIBLE_TEXT = " is not visible";

    public static boolean isElementVisible(WebElement webElement) {
        try {
            return webElement.isDisplayed() && !webElement.getAttribute("class").contains("display: none");
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void assertElementVisible(String elementName, WebElement webElement) {
        Assert.assertTrue(elementName + NOT_VISIBLE_TEXT, isElementVisible(webElement));
    }

}
