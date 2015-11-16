import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by User on 16.11.2015.
 */
public class CharacterCreate {


    protected static final Logger logger = Logger.getLogger(CharacterCreate.class);

    public static final String APP_URL = "http://character-gen.herokuapp.com/view/personage_manager.html";
    private static final String NAME = "TestName";
    private static final String AGE = "50";
    private static final String EXP = "200";

    @Test
    public void testGmailLogin() {
        // objects and variables instantiation
        logger.info("Running driver...");
        WebDriver driver = new FirefoxDriver();
        CharacterCreationPage characterCreationPage = PageFactory.initElements(driver, CharacterCreationPage.class);
        logger.info("Launch browser...");
        characterCreationPage.open(APP_URL);
        logger.info("Maximize browser window...");
        driver.manage().window().maximize();
        logger.info("Set implicity wait...");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        logger.info("Test has started...");

        try {
            characterCreationPage.characterCreateButton().click();
            characterCreationPage.inputCharacterName(NAME);
            characterCreationPage.inputCharacterAge(AGE);
            characterCreationPage.inputCharacterExp(EXP);
            characterCreationPage.CharacterSubmitButton().click();

            Assert.assertEquals("Age doesn`t match", AGE, characterCreationPage.ageLabel().getText());
            Assert.assertEquals("Exp doesn`t match", EXP, characterCreationPage.expLabel().getText());
            characterCreationPage.successCreationLabel().waitUntilNotVisible();
            characterCreationPage.deleteCharacterLink().click();
            characterCreationPage.characterLink().waitUntilNotVisible();

        } finally {
            logger.info("Shutting down driver...");
            driver.close();
            driver.quit();
        }
        logger.info("Test script executed successfully.");
    }
}