package stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.DriverFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingStepDefs {
    private WebDriver driver;

    @Given("I am logged in as {string} with password {string}")
    public void i_am_logged_in_as_with_password(String user, String pwd) {
        driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(pwd);
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "Login failed, not on inventory page.");
    }

    @When("I sort products by {string}")
    public void i_sort_products_by(String sortOption) {
        WebElement dropdown = driver.findElement(By.className("product_sort_container"));
        Select select = new Select(dropdown);
        select.selectByVisibleText(sortOption);
    }

    @Then("products should be displayed in ascending alphabetical order")
    public void products_should_be_displayed_in_ascending_alphabetical_order() {
        List<WebElement> items = driver.findElements(By.className("inventory_item_name"));
        List<String> actual = new ArrayList<>();
        for (WebElement item : items) {
            actual.add(item.getText());
        }
        List<String> expected = new ArrayList<>(actual);
        Collections.sort(expected);
        Assert.assertEquals(actual, expected, "Products are not sorted A to Z");
    }

    @Then("products should be displayed in descending alphabetical order")
    public void products_should_be_displayed_in_descending_alphabetical_order() {
        List<WebElement> items = driver.findElements(By.className("inventory_item_name"));
        List<String> actual = new ArrayList<>();
        for (WebElement item : items) {
            actual.add(item.getText());
        }
        List<String> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());
        Assert.assertEquals(actual, expected, "Products are not sorted Z to A");
    }

    @Then("products should be displayed in ascending price order")
    public void products_should_be_displayed_in_ascending_price_order() {
        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        List<Double> actual = new ArrayList<>();
        for (WebElement price : prices) {
            actual.add(Double.parseDouble(price.getText().replace("$", "")));
        }
        List<Double> expected = new ArrayList<>(actual);
        Collections.sort(expected);
        Assert.assertEquals(actual, expected, "Products are not sorted low to high");
    }

    @Then("products should be displayed in descending price order")
    public void products_should_be_displayed_in_descending_price_order() {
        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        List<Double> actual = new ArrayList<>();
        for (WebElement price : prices) {
            actual.add(Double.parseDouble(price.getText().replace("$", "")));
        }
        List<Double> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());
        Assert.assertEquals(actual, expected, "Products are not sorted high to low");
    }
}