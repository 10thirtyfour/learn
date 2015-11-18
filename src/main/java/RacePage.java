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

    public void inputAttributeBaseCost(String cost) {
        baseCostInput().type(cost);
    }





}