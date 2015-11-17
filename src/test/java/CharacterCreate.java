import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;


/**
 * Created by User on 16.11.2015.
 */
public class CharacterCreate extends BaseTest {
    protected static final Logger logger = Logger.getLogger(CharacterCreate.class);
    private static final String NAME = "TestName";
    private static final String AGE = "50";
    private static final String EXP = "200";

    CharacterCreationPage characterCreationPage = PageFactory.initElements(driver, CharacterCreationPage.class);

    @Override
    public void setUp() throws InterruptedException {
        super.setUp();
        characterCreationPage.open();
        while (characterCreationPage.characterLink().isCurrentlyVisible()) {
            characterCreationPage.deleteCharacterLink().click();
            DriverUtils.waitTimeOut(2000);
        }
    }

    @Test
    public void testGmailLogin() throws InterruptedException {
        Assert.assertFalse(characterCreationPage.characterLink().isCurrentlyVisible());
        logger.info("Creating character...");
        characterCreationPage.characterCreateButton().click();
        characterCreationPage.inputCharacterName(NAME);
        characterCreationPage.inputCharacterAge(AGE);
        characterCreationPage.inputCharacterExp(EXP);
        characterCreationPage.characterSubmitButton().click();
        logger.info("Verifying Character creation");
        Assert.assertTrue(characterCreationPage.characterLink().isVisible());
        logger.info("Verifying Character Age");
        Assert.assertEquals("Age doesn`t match", AGE, characterCreationPage.ageLabel().getText());
        logger.info("Verifying Character Exp.");
        Assert.assertEquals("Exp doesn`t match", EXP, characterCreationPage.expLabel().getText());
        characterCreationPage.successCreationLabel().waitUntilNotVisible();
        logger.info("Test script executed successfully.");
    }

    @Override
    public void tearDown() {
        try {
            //TODO: если тест упал, то перс не создался, а тут мы проверяем, что если создался то удалить, а если нет то ничего не делать
            if (characterCreationPage.deleteCharacterLink().isCurrentlyVisible()) {
                characterCreationPage.deleteCharacterLink().click();
                characterCreationPage.characterLink().waitUntilNotVisible();
            }
            //TODO: закомментировал, потому что эта часть уже не тест, а тест на удаление должен быть отдельно
//            Assert.assertFalse(characterCreationPage.characterLink().isCurrentlyVisible());
        } finally {
            logger.info("Shutting down driver...");
            driver.close();
            driver.quit();
        }
    }
}