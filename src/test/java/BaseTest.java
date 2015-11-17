import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by artemk on 11/17/15.
 */
public class BaseTest {
    protected static final Logger logger = Logger.getLogger(BaseTest.class);
    public static final String APP_URL = "http://character-gen.herokuapp.com";
    WebDriver driver = new FirefoxDriver();
    @Before
    public void setUp() throws InterruptedException {
        logger.info("Launch browser...");
        driver.get(APP_URL);
        logger.info("Maximize browser window...");
        driver.manage().window().maximize();
        logger.info("Set implicity wait...");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        logger.info("Test has started...");
    }

    @After
    public void tearDown() {

    }

}
