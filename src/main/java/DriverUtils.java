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

    public static boolean isElementVisible(WebDriver driver, String locator) {
        try {
            WebElement element;
            if (locator.contains("//")) {
                element = driver.findElement(By.xpath(locator));
            } else {
                element = driver.findElement(By.id(locator));
            }
            return element.isDisplayed() && !element.getAttribute("class").contains("display: none");
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void assertElementVisible(String elementName, WebDriver driver, String locator) {
        Assert.assertTrue(elementName + NOT_VISIBLE_TEXT, isElementVisible(driver, locator));
    }

}
