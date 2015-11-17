import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by artemk on 11/11/15.
 */
public class DriverUtils {

    public static void waitTimeOut(long timeOut) throws InterruptedException {
        Thread.sleep(timeOut);
    }

}
