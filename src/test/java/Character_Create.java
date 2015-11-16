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
    String name = "TestName";
    String age = "50";
    String exp = "200";

    String charLinkXpath = "//*[contains(text(),'"+name+"')]";

    public String findCharacterId (WebDriver driver, String name)
    {
        WebElement characterLink = driver.findElement(By.xpath(charLinkXpath));
        String linkText = characterLink.getAttribute("href");
        String[] parts = linkText.split("=");
        String characterLinkId = parts[1];
        return (characterLinkId);
    }

    @Test
    public void testGmailLogin() {
        // objects and variables instantiation
        logger.info("Running driver...");
        WebDriver driver = new FirefoxDriver();
        CharacterCreationPage characterCreationPage = PageFactory.initElements(driver,CharacterCreationPage.class);
        logger.info("Launch browser...");
        characterCreationPage.open(APP_URL);
        logger.info("Maximize browser window...");
        driver.manage().window().maximize();
        logger.info("Set implicity wait...");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        logger.info("Test has started...");

        try {
            characterCreationPage.characterCreateButton().click();
            characterCreationPage.inputCharacterName(name);
            characterCreationPage.inputCharacterAge(age);
            characterCreationPage.inputCharacterExp(exp);
            characterCreationPage.CharacterSubmitButton().click();

            String deleteButtonLinkXpath = "//*[@name='id' and @value='"+
                    findCharacterId(driver,name)+
                    "']/following-sibling::a[@class='deletePersonage']";
            driver.navigate().refresh();
            Assert.assertEquals ("Age doesn`t match", age, driver.findElement(By.xpath(charLinkXpath+"/parent::*/following-sibling::td[1]")).getText());
            Assert.assertEquals ("Exp doesn`t match", exp, driver.findElement(By.xpath(charLinkXpath+"/parent::*/following-sibling::td[3]")).getText());
            driver.findElement(By.xpath(deleteButtonLinkXpath)).click();

        } finally {
            logger.info("Shutting down driver...");
            driver.close();
            driver.quit();
        }
        logger.info("Test script executed successfully.");
    }
}