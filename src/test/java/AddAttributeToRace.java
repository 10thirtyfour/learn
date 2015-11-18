import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by User on 18.11.2015.
 */
public class AddAttributeToRace extends BaseTest {
    protected static final Logger logger = Logger.getLogger(AddAttributeToRace.class);


    AttributeCreationPage attributeCreationPage = PageFactory.initElements(driver, AttributeCreationPage.class);
    RaceCreationPage raceCreationPage = PageFactory.initElements(driver,RaceCreationPage.class);
    RacePage racePage = PageFactory.initElements(driver,RacePage.class);

    @Override
    public void setUp() throws InterruptedException {
        super.setUp();
        attributeCreationPage.open();

    }

    @Test
    public void testGmailLogin() throws InterruptedException {
        logger.info("Creating attribute...");
        attributeCreationPage.attributeCreateButton().click();
        attributeCreationPage.inputAttributeName(Constants.ATTRIBUTE_NAME1);
        attributeCreationPage.attributeSubmitButton().click();
        logger.info("Verifying Attribute creation");
        Assert.assertTrue(attributeCreationPage.attribute1().isVisible());
        attributeCreationPage.successCreationLabel().waitUntilNotVisible();
        attributeCreationPage.racePageLink().click();
        logger.info("Creating race...");
        raceCreationPage.raceCreateButton().click();
        raceCreationPage.inputRaceName(Constants.RACE_NAME);
        raceCreationPage.raceSubmitButton().click();
        logger.info("Verifying Race creation");
        Assert.assertTrue(raceCreationPage.raceLink().isVisible());
        raceCreationPage.successCreationLabel().waitUntilNotVisible();
        logger.info("Opening Race page");
        raceCreationPage.raceLink().click();
        logger.info("Linking Attribute to Race");
        racePage.linkAttributeToRaceButton().click();
        racePage.raceAttribute().click();
        racePage.inputAttributeBaseCost(Constants.ATTRIBUTE_COST1);
        racePage.linkAttributeToRaceSubmitButton().click();





    }

    public void tearDown() {
        logger.info("Shutting down driver...");
        driver.close();
        driver.quit();
    }
}