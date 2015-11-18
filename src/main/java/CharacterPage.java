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
    private WebElement increaseAttrValueButton;


    public WebElementFacade characterAttribute1() {
        return element(characterAttribute1);
    }

    public WebElementFacade increaseAttrValueButton() {
        return element(increaseAttrValueButton);
    }
}