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
    AttributeCreationPage attributeCreationPage = PageFactory.initElements(driver, AttributeCreationPage.class);
    RaceCreationPage raceCreationPage = PageFactory.initElements(driver, RaceCreationPage.class);
    RacePage racePage = PageFactory.initElements(driver, RacePage.class);
    CharacterCreationPage characterCreationPage = PageFactory.initElements(driver, CharacterCreationPage.class);
    CharacterPage characterPage = PageFactory.initElements(driver, CharacterPage.class);

    public void setAttributeList() {
        Constants.attributeList.add(new Attribute(Constants.ATTRIBUTE_NAME1, Constants.ATTRIBUTE_COST1));
        Constants.attributeList.add(new Attribute(Constants.ATTRIBUTE_NAME2, Constants.ATTRIBUTE_COST2));
        Constants.attributeList.add(new Attribute(Constants.ATTRIBUTE_NAME3, Constants.ATTRIBUTE_COST3));
        Constants.attributeList.add(new Attribute(Constants.ATTRIBUTE_NAME4, Constants.ATTRIBUTE_COST4));
        Constants.attributeList.add(new Attribute(Constants.ATTRIBUTE_NAME5, Constants.ATTRIBUTE_COST5));
    }


    public void inputAttribute(String name) {
        attributeCreationPage.attributeCreateButton().click();
        attributeCreationPage.inputAttributeName(name);
        attributeCreationPage.attributeSubmitButton().click();
        attributeCreationPage.successCreationLabel().waitUntilNotVisible();
    }

    public void linkAttributeToRace(String name,String cost) {
        racePage.linkAttributeToRaceButton().click();
        racePage.selectAttributeByName(name).click();
        racePage.inputAttributeBaseCost(cost);
        racePage.linkAttributeToRaceSubmitButton().click();
        racePage.successCreationLabel().waitUntilNotVisible();
    }

    public Integer attributeIncrease(String name,String cost) {
        characterPage.getIncreaseAttributeValueButtonByName(name).click();
        Integer attribute_cost = Integer.parseInt(cost);
        expCost = expCost + attribute_cost;
        return expCost;
    }

    @Override
    public void setUp() throws InterruptedException {
        super.setUp();
        attributeCreationPage.open();

    }


    @Test
    public void testGmailLogin() throws InterruptedException {
        setAttributeList();
        logger.info("Creating attributes...");
        for (int i = 0; i < 5; i++) {
            inputAttribute(Constants.attributeList.get(i).name);
        }
        System.out.println(Constants.attributeList.get(4).name);

        logger.info("Verifying Attribute creation");
        for (int i = 0; i < 5; i++) {
            Assert.assertTrue(attributeCreationPage.getAttributeByName(Constants.attributeList.get(i).name).isVisible());
        }
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
        for (int i = 0; i < 5; i++)
        {
            linkAttributeToRace(Constants.attributeList.get(i).name,Constants.attributeList.get(i).baseCost );
        }

        logger.info("Verifying Attribute linking");
        for (int i = 0; i < 5; i++) {
            Assert.assertTrue(racePage.getRaceAttributeByName(Constants.attributeList.get(i).name).isVisible());
        }
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
        logger.info("Verifying Attributes present on Character page");
        for (int i=0;i<5;i++) {
            Assert.assertTrue(characterPage.getCharacterAttributeByName(Constants.attributeList.get(i).name).isVisible());
        }
        for (int i=0;i<5;i++) {
            logger.info("Increasing " + Constants.attributeList.get(i).name + " attribute");
            logger.info("Current Exp cost " + attributeIncrease(Constants.attributeList.get(i).name,Constants.attributeList.get(i).baseCost));
        }
            logger.info("Returning to Character Creation page...");
            raceCreationPage.characerCreationPageLink().click();
            expRemaining = Integer.parseInt(Constants.CHARACTER_EXP) - expCost;
            logger.info(expRemaining + "Exp Remaining");
            Assert.assertEquals("Exp doesn`t match", expRemaining.toString(), characterCreationPage.expLabel().getText());

    }

    public void tearDown() {
        logger.info("Shutting down driver...");
        driver.close();
        driver.quit();
    }
    }
