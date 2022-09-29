package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Logout extends BasePage {
    private By button = By.id("react-burger-menu-btn");
    private By logout = By.xpath("//*[@id=\"logout_sidebar_link\"]");

    public Logout(WebDriver driver) {
        super(driver);
    }

    public WebElement getButton() {
        return getDriver().findElement(button);
    }

    public WebElement getLogout() {
        return getDriver().findElement(logout);
    }

    public void logingout() {
       getButton().click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        getLogout().click();
    }
}
