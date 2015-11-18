import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by User on 18.11.2015.
 */
public class RaceCreationPage extends BasicPage {
    public static final String PAGE_URL = DOMAIN + "/view/race_manager.html";

    public RaceCreationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='addRaceButton']/a/button")
    private WebElement raceCreateButton;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "maxAge")
    private WebElement maxAgeInput;

    @FindBy(id = "addRaceSubmitButton")
    private WebElement raceSubmitButton;

    @FindBy(xpath = "//a[contains(text(),'" + Constants.RACE_NAME + "')]")
    private WebElement raceLink;

    public WebElementFacade raceCreateButton() {
        return element(raceCreateButton);
    }

    public WebElementFacade raceLink() {
        return element(raceLink);
    }

    public void inputRaceName(String name) {
        nameInput().type(name);
    }

    public void inputRaceMaxAge(String age) {
        ageInput().type(age);
    }

    public WebElementFacade nameInput() {
        return element(nameInput);
    }

    public WebElementFacade ageInput() {
        return element(maxAgeInput);
    }

    public WebElementFacade raceSubmitButton() {
        return element(raceSubmitButton);
    }

}