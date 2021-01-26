package setup.driver;


import io.cucumber.java.After;
import io.cucumber.java.Scenario;
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
