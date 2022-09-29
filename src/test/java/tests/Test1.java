package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Checkout;
import pages.LoginPage;
import pages.Logout;
import pages.Products;

import java.time.Duration;
import java.time.Instant;

public class Test1 {
    private LoginPage loginPage;
    private Products products;
    private Checkout checkout;
    private Logout logout;

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        products = new Products(driver);
        checkout = new Checkout(driver);
        logout = new Logout(driver);
    }
    @BeforeMethod
    public void beforeMethod() {
        driver.manage().deleteAllCookies();
        driver.get("https://www.saucedemo.com");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testLogin() {
        loginPage.login("standard_user", "secret_sauce");

        String expectedResult = "PRODUCTS";
        String actualResult = loginPage.getDriver().findElement(By.className("title")).getText(); // driver.getTitle();
        Assert.assertEquals(expectedResult,actualResult);
    }
    @Test
    public void addProduct(){
        loginPage.login("standard_user", "secret_sauce");

        products.addingToCart();
        String expectedResult = "YOUR";

        String actualResult = products.getDriver().findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertTrue(actualResult.contains(expectedResult));
    }
    @Test
    public void checkingProduct(){
        loginPage.login("standard_user", "secret_sauce");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        products.addingToCart();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        checkout.buying("t", "j", "123");
        String expectedResult = "CHECKOUT";
        String actualResult =checkout.getDriver().findElement(By.className("title")).getText();
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @Test (priority = 4)
    public void confirmation(){
        loginPage.login("standard_user", "secret_sauce");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        products.addingToCart();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        checkout.buying("t", "j", "123");
        String expectedResult = "Total: $32.39";
        String actualResult =checkout.getDriver().findElement(By.className("summary_total_label")).getText();
        Assert.assertTrue(actualResult.contains(expectedResult));
        checkout.getFinishButton().click();
        String expectedResult1 = "Your order has been dispatched";
        String actualResult1 =checkout.getDriver().findElement(By.className("complete-text")).getText();
        Assert.assertTrue(actualResult1.contains(expectedResult1));
    }
    @Test (priority = 4)
    public void loggingout(){
        loginPage.login("standard_user", "secret_sauce");
        logout.logingout();
        String expectedResult = "Accepted usernames";
        String actualResult =checkout.getDriver().findElement(By.xpath("//*[@id=\"login_credentials\"]/h4")).getText();
        Assert.assertTrue(actualResult.contains(expectedResult));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logout.getDriver().get("https://www.saucedemo.com/cart.html");
        String expectedResult1 = "Epic sadface:";
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String actualResult1 =checkout.getDriver().findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]")).getText();
        Assert.assertTrue(actualResult1.contains(expectedResult1));
    }


}
