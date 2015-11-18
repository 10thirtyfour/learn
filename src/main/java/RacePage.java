import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by User on 18.11.2015.
 */
public class RacePage extends BasicPage {

    public RacePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@id='linkAttributeToRaceButton']/a/div")
    private WebElement linkAttributeToRaceButton;

    @FindBy(id = "linkAttributeToRaceSubmit")
    private WebElement linkAttributeToRaceSubmitButton;

    @FindBy(id = "baseCost")
    private WebElement baseCostInput;

    @FindBy(xpath = "//select[@id='attribute']/option[contains(text(),'" + Constants.ATTRIBUTE_NAME1 + "')]")
    private WebElement raceAttribute;

    @FindBy(xpath = "//td[contains(text(),'" + Constants.ATTRIBUTE_NAME1 + "')]")
    private WebElement attribute1;

    @FindBy(xpath = "//td[contains(text(),'" + Constants.ATTRIBUTE_NAME1 + "')]/following-sibling::td[1]")
    private WebElement attribute1BaseCostDisplay;

    public WebElementFacade linkAttributeToRaceButton() {
        return element(linkAttributeToRaceButton);
    }

    public WebElementFacade linkAttributeToRaceSubmitButton (){
        return element(linkAttributeToRaceSubmitButton);
    }

    public WebElementFacade raceAttribute() {
        return element(raceAttribute);
    }

    public WebElementFacade baseCostInput () {
        return element(baseCostInput);
    }

    public WebElementFacade attribute1 () {
        return element(attribute1);
    }

    public void inputAttributeBaseCost(String cost) {
        baseCostInput().type(cost);
    }





}