package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

public class Hooks {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        System.out.println(">>> Browser started");
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
        System.out.println(">>> Browser closed");
    }
}