import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Created by User on 13.11.2015.
 */
public class CharacterCreationPage extends BasicPage {

    public CharacterCreationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h4")
    private WebElement successCreationLabel;

    @FindBy(xpath = "//div[@id='addPersonageButton']/a/button")
    private WebElement characterCreateButton;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "age")
    private WebElement ageInput;

    @FindBy(id = "experience")
    private WebElement expInput;

    @FindBy(id = "addPersonageSubmitButton")
    private WebElement characterSubmitButton;

    @FindBy(xpath = "//a[contains(text(),'" + Constants.CHARACTER_NAME + "')]")
    private WebElement characterLink;

    @FindBy(xpath = "//tr[td/a[text()='" + Constants.CHARACTER_NAME + "']]/td/a[text()='Удалить']")
    private WebElement deleteCharacterLink;

    @FindBy(xpath = "//tr[td/a[text()='" + Constants.CHARACTER_NAME + "']]/td[2]")
    private WebElement ageLabel;

    @FindBy(xpath = "//tr[td/a[text()='" + Constants.CHARACTER_NAME + "']]/td[4]")
    private WebElement expLabel;

    public void inputCharacterName(String name) {
        nameInput().type(name);
    }

    public void inputCharacterAge(String age) {
        ageInput().type(age);
    }

    public void inputCharacterExp(String age) {
        expInput().type(age);
    }

    public WebElementFacade characterCreateButton() {
        return element(characterCreateButton);
    }

    public WebElementFacade characterSubmitButton() {
        return element(characterSubmitButton);
    }

    public WebElementFacade nameInput() {
        return element(nameInput);
    }

    public WebElementFacade ageInput() {
        return element(ageInput);
    }

    public WebElementFacade expInput() {
        return element(expInput);
    }

    public WebElementFacade characterLink() {
        return element(characterLink);
    }

    public WebElementFacade deleteCharacterLink() {
        return element(deleteCharacterLink);
    }

    public WebElementFacade ageLabel() {
        return element(ageLabel);
    }

    public WebElementFacade expLabel() {
        return element(expLabel);
    }

    public WebElementFacade successCreationLabel() {
        return element(successCreationLabel);
    }
}

