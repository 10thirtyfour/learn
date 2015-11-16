import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by User on 16.11.2015.
 */
public class Character_Create {


    protected static final Logger logger = Logger.getLogger(Character_Create.class);

    public static final String APP_URL = "http://character-gen.herokuapp.com/view/personage_manager.html";
    private static final String NAME = "TestName";
    private static final String AGE = "50";
    private static final String EXP = "200";

    String charLinkXpath = "//*[contains(text(),'" + NAME + "')]";

    public String findCharacterId(WebDriver driver, String name) {
        WebElement characterLink = driver.findElement(By.xpath(charLinkXpath));
        String linkText = characterLink.getAttribute("href");
        String[] parts = linkText.split("=");
        return parts[1];
    }

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

            String deleteButtonLinkXpath = "//*[@name='id' and @value='" +
                    findCharacterId(driver, NAME) +
                    "']/following-sibling::a[@class='deletePersonage']";
            driver.navigate().refresh();
            Assert.assertEquals("Age doesn`t match", AGE, driver.findElement(By.xpath(charLinkXpath + "/parent::*/following-sibling::td[1]")).getText());
            Assert.assertEquals("Exp doesn`t match", EXP, driver.findElement(By.xpath(charLinkXpath + "/parent::*/following-sibling::td[3]")).getText());
            driver.findElement(By.xpath(deleteButtonLinkXpath)).click();

        } finally {
            logger.info("Shutting down driver...");
            driver.close();
            driver.quit();
        }
        logger.info("Test script executed successfully.");
    }
}