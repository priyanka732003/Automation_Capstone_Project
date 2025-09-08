package stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.DriverFactory;

public class BasicStepDefs {
    private WebDriver driver;

    @Given("I open the browser")
    public void i_open_the_browser() {
        driver = DriverFactory.getDriver();
        Assert.assertNotNull(driver, "Browser did not start!");
    }

    @When("I navigate to the SauceDemo URL")
    public void i_navigate_to_the_saucedemo_url() {
        driver.get("https://www.saucedemo.com/");
    }

    @Then("I should see the login page")
    public void i_should_see_the_login_page() {
        boolean visible = driver.findElement(By.id("login-button")).isDisplayed();
        Assert.assertTrue(visible, "Login button not visible → page not loaded properly.");
        DriverFactory.quitDriver();
    }
}