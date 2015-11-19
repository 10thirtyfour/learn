import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 18.11.2015.
 */
public class AddAttributeToRace extends BaseTest {
    protected static final Logger logger = Logger.getLogger(AddAttributeToRace.class);
    public static Integer expCost = 0;
    public static Integer expRemaining = 0;
    Map attributeCostMap = new HashMap();
    AttributeCreationPage attributeCreationPage = PageFactory.initElements(driver, AttributeCreationPage.class);
    RaceCreationPage raceCreationPage = PageFactory.initElements(driver,RaceCreationPage.class);
    RacePage racePage = PageFactory.initElements(driver,RacePage.class);
    CharacterCreationPage characterCreationPage = PageFactory.initElements(driver, CharacterCreationPage.class);
    CharacterPage characterPage = PageFactory.initElements(driver,CharacterPage.class);

    public void setAttributeCostMap(String cost1,String cost2, String cost3, String cost4, String cost5) {
        attributeCostMap.put(Constants.ATTRIBUTE_NAME1, cost1);
        attributeCostMap.put(Constants.ATTRIBUTE_NAME2, cost2);
        attributeCostMap.put(Constants.ATTRIBUTE_NAME3, cost3);
        attributeCostMap.put(Constants.ATTRIBUTE_NAME4, cost4);
        attributeCostMap.put(Constants.ATTRIBUTE_NAME5, cost5);
    }

    public void inputAttribute(String name) {
        attributeCreationPage.attributeCreateButton().click();
        attributeCreationPage.inputAttributeName(name);
        attributeCreationPage.attributeSubmitButton().click();
        attributeCreationPage.successCreationLabel().waitUntilNotVisible();
    }

    public void linkAttributeToRace (String name){
        racePage.linkAttributeToRaceButton().click();
        racePage.selectAttributeByName(name).click();
        racePage.inputAttributeBaseCost(attributeCostMap.get(name).toString());
        racePage.linkAttributeToRaceSubmitButton().click();
        racePage.successCreationLabel().waitUntilNotVisible();
    }

    public Integer attributeIncrease (String name) {
        characterPage.getIncreaseAttributeValueButtonByName(name).click();
       Integer attribute_cost = Integer.parseInt(attributeCostMap.get(name).toString());
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
        setAttributeCostMap("1","2","3","4","5");
        System.out.println(attributeCostMap.get(Constants.ATTRIBUTE_NAME5));
        logger.info("Creating attributes...");
        inputAttribute(Constants.ATTRIBUTE_NAME1);
        inputAttribute(Constants.ATTRIBUTE_NAME2);
        inputAttribute(Constants.ATTRIBUTE_NAME3);
        inputAttribute(Constants.ATTRIBUTE_NAME4);
        inputAttribute(Constants.ATTRIBUTE_NAME5);

        logger.info("Verifying Attribute creation");
        Assert.assertTrue(attributeCreationPage.getAttributeByName(Constants.ATTRIBUTE_NAME1).isVisible());
        Assert.assertTrue(attributeCreationPage.getAttributeByName(Constants.ATTRIBUTE_NAME2).isVisible());
        Assert.assertTrue(attributeCreationPage.getAttributeByName(Constants.ATTRIBUTE_NAME3).isVisible());
        Assert.assertTrue(attributeCreationPage.getAttributeByName(Constants.ATTRIBUTE_NAME4).isVisible());
        Assert.assertTrue(attributeCreationPage.getAttributeByName(Constants.ATTRIBUTE_NAME5).isVisible());
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
        logger.info("Linking Attributes to Race...");
        linkAttributeToRace(Constants.ATTRIBUTE_NAME1);
        linkAttributeToRace(Constants.ATTRIBUTE_NAME2);
        linkAttributeToRace(Constants.ATTRIBUTE_NAME3);
        linkAttributeToRace(Constants.ATTRIBUTE_NAME4);
        linkAttributeToRace(Constants.ATTRIBUTE_NAME5);
        logger.info("Verifying Attribute linking");
        Assert.assertTrue(racePage.getRaceAttributeByName(Constants.ATTRIBUTE_NAME1).isVisible());
        Assert.assertTrue(racePage.getRaceAttributeByName(Constants.ATTRIBUTE_NAME2).isVisible());
        Assert.assertTrue(racePage.getRaceAttributeByName(Constants.ATTRIBUTE_NAME3).isVisible());
        Assert.assertTrue(racePage.getRaceAttributeByName(Constants.ATTRIBUTE_NAME4).isVisible());
        Assert.assertTrue(racePage.getRaceAttributeByName(Constants.ATTRIBUTE_NAME5).isVisible());
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
        logger.info("Increasing"+Constants.ATTRIBUTE_NAME1+"attribute");
        logger.info("Current Exp cost"+attributeIncrease (Constants.ATTRIBUTE_NAME1));
        logger.info("Increasing"+Constants.ATTRIBUTE_NAME2+"attribute");
        logger.info("Current Exp cost"+attributeIncrease (Constants.ATTRIBUTE_NAME2));
        logger.info("Increasing"+Constants.ATTRIBUTE_NAME3+"attribute");
        logger.info("Current Exp cost"+attributeIncrease (Constants.ATTRIBUTE_NAME3));
        logger.info("Increasing"+Constants.ATTRIBUTE_NAME4+"attribute");
        logger.info("Current Exp cost"+attributeIncrease (Constants.ATTRIBUTE_NAME4));
        logger.info("Increasing"+Constants.ATTRIBUTE_NAME5+"attribute");
        logger.info("Current Exp cost"+attributeIncrease (Constants.ATTRIBUTE_NAME5));
        logger.info("Returning to Character Creation page...");
        raceCreationPage.characerCreationPageLink().click();
        expRemaining = Integer.parseInt(Constants.CHARACTER_EXP)-expCost;
        logger.info(expRemaining+"Exp Remaining");
        Assert.assertEquals("Exp doesn`t match",expRemaining.toString() , characterCreationPage.expLabel().getText());

    }

    public void tearDown() {
        logger.info("Shutting down driver...");
        driver.close();
        driver.quit();
    }
}