package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Checkout extends BasePage {


    private By checkout = By.id("checkout");
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");

    public Checkout(WebDriver driver) {
        super(driver);
    }

    public WebElement getCheckout() {
        return getDriver().findElement(checkout);
    }

    public WebElement getFirstName() {
        return getDriver().findElement(firstName);
    }

    public WebElement getLastName() {
        return getDriver().findElement(lastName);
    }

    public WebElement getPostalCode() {
        return getDriver().findElement(postalCode);
    }

    public WebElement getContinueButton() {
        return getDriver().findElement(continueButton);
    }

    public WebElement getFinishButton() {
        return getDriver().findElement(finishButton);
    }





    public void buying(String firstname, String lastname, String postalcode) {

        getCheckout().click();
        getFirstName().sendKeys(firstname);
        getLastName().sendKeys(lastname);
        getPostalCode().sendKeys(postalcode);
        getContinueButton().click();
    }
}
