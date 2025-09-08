package stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.DriverFactory;

import java.time.Duration;

public class LoginStepDefs {
    private WebDriver driver;

    @Given("I am on the SauceDemo login page")
    public void i_am_on_the_saucedemo_login_page() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com/");
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String user, String pwd) {
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys(user);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(pwd);
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("I should be redirected to the inventory page")
    public void i_should_be_redirected_to_the_inventory_page() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.urlContains("inventory.html"));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "Expected inventory page, got: " + driver.getCurrentUrl());
    }

    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String expected) {
        String actual = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")))
            .getText();
        Assert.assertEquals(actual, expected);
    }
}