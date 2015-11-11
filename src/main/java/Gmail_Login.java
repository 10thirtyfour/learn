/**
 * Created by User on 06.11.2015.
 */


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Gmail_Login {
    protected static final Logger logger = Logger.getLogger(Gmail_Login.class);

    public static final String APP_URL = "https://accounts.google.com";

    /**
     * 8
     *
     * @param args 9
     */
    public static void main(String[] args) {
        // objects and variables instantiation
        logger.info("Running driver...");
        WebDriver driver = new FirefoxDriver();
        logger.info("Launch browser...");
        driver.get(APP_URL);
        logger.info("Maximize browser window...");
        driver.manage().window().maximize();
        logger.info("Test has started...");
        String expectedTitle = "Sign in - Google Accounts";
        String actualTitle = driver.getTitle();
        Assert.assertEquals("Title is incorrect", expectedTitle, actualTitle);
        logger.info("verifying Logo");
        try {
            driver.findElement(By.xpath("//*[contains(@class,'logo logo-w')]"));
            // "//div[contains(@class,'logo logo-w')]" - лучше указывать имя конкретного элемента, в данном случае div
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("Logo is not present", e);
        }

        logger.info("verifying Sign in message");

        try {
            driver.findElement(By.xpath("//*[contains(text(),'Sign in with your Google Account')]"));
            // "//h2" - если обратишь внимание на этой странице всего 1 элемент h2, ну и по правилам хорошего html он долженбыть 1 на странцице,
            // коротко, уникально, понятно, кроме того текст который ты использовал может меняться
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("Sign in message is not present", e);
        }

        logger.info("verifying Canvas");

        try {
            driver.findElement(By.id("canvas"));
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("Canvas is not present", e);
        }

        logger.info("verifying Need Help link");

        try {
            driver.findElement(By.xpath("//*[contains(@class,'need-help')]"));
            // "//input[@id='next']/following-sibling::a" - здесь я использую привязку к элементу, который имеет id,
            // что стабилизирует xpath
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("Need Help link is not present", e);
        }

        logger.info("verifying Create Account link");

        try {
            driver.findElement(By.xpath("//*[contains(text(),'Create account')]"));
            // "//span[@id='link-signup']/a" у элемента span в котором находится нужный объект есть id, почему бы нам его не использовать?
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("Create Account link is not present", e);
        }

        WebElement username = driver.findElement(By.id("Email"));
        username.clear();
        username.sendKeys("TestSelenium");
        WebElement NextButton = driver.findElement(By.id("next"));
        NextButton.click();
        WebElement password = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("Passwd")));
        password.clear();
        password.sendKeys("password123");
        WebElement SignInButton = driver.findElement(By.id("signIn"));
        SignInButton.click();

        logger.info("verifying Error Message");

        try {
            WebElement errorMessage = driver.findElement(By.xpath("//*[contains(@id,'errormsg_0_Passwd')]"));
            // ну тут можно было тупо id использовать, ну либо "//span[@id='errormsg_0_Passwd']"
            String actualError = errorMessage.getText();
            String expectedError = "The email and password you entered don't match.";
            Assert.assertEquals("Error Message is incorrect", expectedError, actualError);

        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("Error Message is not present, e");
        }

        //Насчет проверки, что инпут пароля стал с красным ободком
        //1. так как страница перезагрузилась, нужно перечитать элемент пасворд, как этого избежать расскажу позже
        //2. инпут пасворд не имеет никакого класса, до того как появится ошибка class=""
        //3. после того как ошибка появилась и инпут стал с красным ободком у инпута появляется класс class="form-error"
        // это мы и проверяем, что при появлении ошибки у инпута стал не пустой класс, ну или можно проверить конкретно
        // на имя класса, но думаю это уже будет лишнее.
        // ну и если хочешь убедится что именно этот класс отвечает за подкрашивание красным, ты можешь пойти в css
        // файлы и найти там класс с именем form-error и посмотреть какие стили применяются в том классе,
        // там ты увидишь бордер красного цвета

        logger.info("verifying red border of password field");
        password = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("Passwd")));
        Assert.assertFalse(password.getAttribute("class").isEmpty());

        driver.close();
        logger.info("Test script executed successfully.");
        driver.quit();
    }
}

