package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Products extends BasePage {
    private By product1 = By.xpath("//*[@id=\"inventory_container\"]/div/div[1]");

    private By addtoCart = By.id("add-to-cart-sauce-labs-backpack");

    private By cart = By.id("shopping_cart_container");


    public Products(WebDriver driver) {
        super(driver);
    }

    public WebElement getProduct1() {
        return getDriver().findElement(product1);
    }


    public WebElement getAddtoCart() {
        return getDriver().findElement(addtoCart);
    }

    public WebElement getCart() {
        return getDriver().findElement(cart);
    }

    public void addingToCart() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getProduct1()).perform();
        getAddtoCart().click();
        getCart().click();
    }



}
