package setup.driver;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.openqa.selenium.WebDriver;

public class TearDown {

    private final WebDriver driver;

    public TearDown() {
        this.driver = Setup.driver;
    }

    @After
    public void quitDriver(Scenario scenario) {
        this.driver.quit();
    }
}
