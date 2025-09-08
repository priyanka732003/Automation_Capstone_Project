package stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.DriverFactory;

import java.time.Duration;
import java.util.Locale;

public class CartStepDefs {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CartStepDefs() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // convert product name to saucedemo slug
    private String toSlug(String name) {
        return name.toLowerCase(Locale.ROOT)
                .replace(" ", "-")
                .replace("(", "").replace(")", "")
                .replace(".", "");
    }

    private By addBtn(String product) {
        return By.id("add-to-cart-" + toSlug(product));
    }

    private By cartBadge() {
        return By.className("shopping_cart_badge");
    }

    private void waitForBadgeCount(int expected) {
        wait.until(d -> {
            var badges = d.findElements(cartBadge());
            if (expected == 0) return badges.isEmpty();
            if (badges.isEmpty()) return false;
            return badges.get(0).getText().trim().equals(String.valueOf(expected));
        });
    }

    @When("I add {string} to the cart")
    public void i_add_item_to_the_cart(String item) {
        wait.until(ExpectedConditions.elementToBeClickable(addBtn(item))).click();
    }

    @Then("the cart badge should show {string}")
    public void the_cart_badge_should_show(String expected) {
        waitForBadgeCount(Integer.parseInt(expected.trim()));
        Assert.assertEquals(driver.findElement(cartBadge()).getText().trim(), expected);
    }
}