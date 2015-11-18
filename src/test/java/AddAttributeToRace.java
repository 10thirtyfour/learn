import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by User on 18.11.2015.
 */
public class AddAttributeToRace extends BaseTest {
    protected static final Logger logger = Logger.getLogger(AddAttributeToRace.class);
    public static Integer expCost = 0;


    AttributeCreationPage attributeCreationPage = PageFactory.initElements(driver, AttributeCreationPage.class);
    RaceCreationPage raceCreationPage = PageFactory.initElements(driver,RaceCreationPage.class);
    RacePage racePage = PageFactory.initElements(driver,RacePage.class);
    CharacterCreationPage characterCreationPage = PageFactory.initElements(driver, CharacterCreationPage.class);
    CharacterPage characterPage = PageFactory.initElements(driver,CharacterPage.class);

    public Integer attributeIncrease (WebElementFacade button, String cost) {
        button.click();
       Integer attribute_cost = Integer.parseInt(cost);
               expCost = expCost+attribute_cost;
        return expCost;
    }

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
        logger.info("Opening Race page...");
        raceCreationPage.raceLink().click();
        logger.info("Linking Attribute to Race...");
        racePage.linkAttributeToRaceButton().click();
        racePage.raceAttribute().click();
        racePage.inputAttributeBaseCost(Constants.ATTRIBUTE_COST1);
        racePage.linkAttributeToRaceSubmitButton().click();
        logger.info("Verifying Attribute linking");
        Assert.assertTrue(racePage.attribute1().isVisible());
        raceCreationPage.successCreationLabel().waitUntilNotVisible();
        logger.info("Opening Character Creation page...");
        raceCreationPage.characerCreationPageLink().click();
        logger.info("Creating Character...");
        characterCreationPage.characterCreateButton().click();
        characterCreationPage.inputCharacterName(Constants.CHARACTER_NAME);
        characterCreationPage.inputCharacterAge(Constants.CHARACTER_AGE);
        characterCreationPage.inputCharacterExp(Constants.CHARACTER_EXP);
        characterCreationPage.characterRace().click();
        characterCreationPage.characterSubmitButton().click();
        logger.info("Verifying Character creation");
        Assert.assertTrue(characterCreationPage.characterLink().isVisible());
        characterCreationPage.successCreationLabel().waitUntilNotVisible();
        logger.info("Opening Character page...");
        characterCreationPage.characterLink().click();
        logger.info("Verifying Attribute present on Character page");
        Assert.assertTrue(characterPage.characterAttribute1().isVisible());
        logger.info("Current Exp cost"+attributeIncrease (characterPage.increaseAttrValueButton1(),Constants.ATTRIBUTE_COST1));














    }

   // public void tearDown() {
   //     logger.info("Shutting down driver...");
   //     driver.close();
   //     driver.quit();
   // }
}