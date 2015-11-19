import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by User on 18.11.2015.
 */
public class CharacterPage extends BasicPage {

    public CharacterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//td[contains(text(),'" + Constants.ATTRIBUTE_NAME1 + "')]")
    private WebElement characterAttribute1;

    @FindBy(xpath = "//td[contains(text(),'" + Constants.ATTRIBUTE_NAME1 + "')]/following-sibling::td/a[@class='plusAttribute']")
    private WebElement increaseAttrValueButton1;

    @FindBy(xpath = "//td[contains(text(),'" + Constants.ATTRIBUTE_NAME1 + "')]/following-sibling::td[@name='currentValue']")
    private WebElement attribute1CurrentValue;

    public WebElementFacade getCharacterAttributeByName(String attributeName) {
        return element(driver.findElement(By.xpath("//td[contains(text(),'" + attributeName + "')]")));
    }

    public WebElementFacade getIncreaseAttributeValueButtonByName(String attributeName) {
        return element(driver.findElement(By.xpath("//td[contains(text(),'"
                + attributeName + "')]/following-sibling::td/a[@class='plusAttribute']")));
    }


    public WebElementFacade characterAttribute1() {
        return element(characterAttribute1);
    }

    public WebElementFacade increaseAttrValueButton1() {
        return element(increaseAttrValueButton1);
    }

    public WebElementFacade attribute1CurrentValue() {
        return element(attribute1CurrentValue);
    }
}

//td[contains(text(),'TestAttribute1')]/following-sibling::td/a[@name='currentValue']