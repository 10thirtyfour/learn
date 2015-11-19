import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by User on 18.11.2015.
 */
public class AttributeCreationPage extends BasicPage {
    public static final String PAGE_URL = DOMAIN + "/view/attribute_manager.html";

    public AttributeCreationPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@id='addAttributeButton']/a/button")
    private WebElement attributeCreateButton;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "addAttributeSubmitButton")
    private WebElement attributeSubmitButton;

    @FindBy(xpath = "//td[contains(text(),'" + Constants.ATTRIBUTE_NAME1 + "')]")
    private WebElement attribute1;

    public WebElementFacade getAttributeByName(String attributeName) {
        return element(driver.findElement(By.xpath("//td[contains(text(),'" + attributeName + "')]")));
    }

    public WebElementFacade attribute1() {
        return element(attribute1);
    }
    public WebElementFacade attributeCreateButton() {
        return element(attributeCreateButton);
    }

    public WebElementFacade nameInput() {
        return element(nameInput);
    }

    public WebElementFacade attributeSubmitButton() {
        return element(attributeSubmitButton);
    }

    public void  inputAttributeName(String name) {
        nameInput().type(name);
    }

    public void open() {
        driver.navigate().to(PAGE_URL);
    }

}

