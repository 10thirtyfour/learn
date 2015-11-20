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

    public void linkAttributeToRace(String name, String cost) {
        racePage.linkAttributeToRaceButton().click();
        racePage.selectAttributeByName(name).click();
        racePage.inputAttributeBaseCost(cost);
        racePage.linkAttributeToRaceSubmitButton().click();
        racePage.successCreationLabel().waitUntilNotVisible();
    }

    public Integer attributeIncrease(String name, String cost) {
        characterPage.getIncreaseAttributeValueButtonByName(name).click();
        Integer attribute_cost = Integer.parseInt(cost);
        int expCost = 0;
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

        for (Attribute attribute : Constants.attributeList) {
            inputAttribute(attribute.name);
        }

        logger.info("Verifying Attribute creation");
        for (Attribute attribute : Constants.attributeList) {
            Assert.assertTrue(attributeCreationPage.getAttributeByName(attribute.name).isVisible());
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

        for (Attribute attribute : Constants.attributeList) {
            linkAttributeToRace(attribute.name, attribute.baseCost);
        }

        logger.info("Verifying Attribute linking");
        for (Attribute attribute : Constants.attributeList) {
            Assert.assertTrue(racePage.getRaceAttributeByName(attribute.name).isVisible());
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

        for (Attribute attribute : Constants.attributeList) {
            Assert.assertTrue(characterPage.getCharacterAttributeByName(attribute.name).isVisible());
        }

        int totalAttributesExpCost = 0;
        for (Attribute attribute : Constants.attributeList) {
            logger.info("Increasing " + attribute.name + " attribute");
            logger.info("Current Exp cost " + attributeIncrease(attribute.name, attribute.baseCost));
            totalAttributesExpCost = totalAttributesExpCost + attributeIncrease(attribute.name, attribute.baseCost);
        }
        logger.info("Returning to Character Creation page...");
        raceCreationPage.characerCreationPageLink().click();
        Integer expRemaining = Integer.parseInt(Constants.CHARACTER_EXP) - totalAttributesExpCost;
        logger.info(expRemaining + "Exp Remaining");
        Assert.assertEquals("Exp doesn`t match", expRemaining.toString(), characterCreationPage.expLabel().getText());

    }

    public void tearDown() {
        logger.info("Shutting down driver...");
        driver.close();
        driver.quit();
    }
}
