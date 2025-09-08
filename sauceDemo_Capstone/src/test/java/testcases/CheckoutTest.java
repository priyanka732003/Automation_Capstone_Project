package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.LoginPage;
import pages.HomePage;
import pages.CartPage;
import pages.CheckoutPage;
import utils.DriverFactory;

import java.time.Duration;

public class CheckoutTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.initDriver("chrome");
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // login
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        System.out.println("✅ Logged in");
    }
    
    @Test
    public void testValidCheckout() {
        // Step 1: Add items
        homePage.addBackpack();
        homePage.addBikeLight();
        homePage.clickCart();
        wait.until(ExpectedConditions.urlContains("cart.html"));

        // Step 2: Checkout
        cartPage.clickCheckout();
        wait.until(ExpectedConditions.urlContains("checkout-step-one"));

        // Step 3: Enter valid details
        checkoutPage.enterDetails("John", "Doe", "12345");
        checkoutPage.clickContinue();
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));

        // Step 4: Finish
        checkoutPage.clickFinish();
        wait.until(ExpectedConditions.urlContains("checkout-complete"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));

        // Step 5: Verify last page content
        String message = checkoutPage.getSuccessMsg();
        Assert.assertTrue(message.equalsIgnoreCase("Thank you for your order!"),
                "❌ Expected success message not found! Actual: " + message);

        String dispatch = checkoutPage.getDispatchMsg();
        Assert.assertTrue(dispatch.contains("Your order has been dispatched"),
                "❌ Dispatch text not found! Actual: " + dispatch);

        Assert.assertTrue(checkoutPage.isPonyImageDisplayed(),
                "❌ Pony Express image not displayed!");

        System.out.println("✅ Valid checkout passed with full verification!");
    }
 @Test
    public void testInvalidCheckout() {
        // Step 1: Add item
        homePage.addBackpack();
        homePage.clickCart();
        wait.until(ExpectedConditions.urlContains("cart.html"));

        // Step 2: Checkout
        cartPage.clickCheckout();
        wait.until(ExpectedConditions.urlContains("checkout-step-one"));

        // Step 3: Leave details empty
        checkoutPage.enterDetails("", "", "");
        checkoutPage.clickContinue();

        // Step 4: Verify error message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
        String error = checkoutPage.getErrorMsg();
        Assert.assertTrue(error.contains("Error"), "Expected error not found!");
        System.out.println("Invalid checkout shows error: " + error);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000); // pause for viewing
        DriverFactory.quitDriver();
    }
}
