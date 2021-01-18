package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    public Driver() {}

    public static  WebDriver driver;
    public static final String AUTOMATE_USERNAME = "markomirtic2";
    public static final String AUTOMATE_ACCESS_KEY = "P1vgaPzT4btgU1cLjkrg";
    public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static WebDriver get() throws MalformedURLException {
        if (driver == null) {
            String browser = ConfigurationReader.get("browser");
            System.out.println("browser = " + browser);
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "chromeHeadless":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "firefoxHeadless":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
                    break;

                case "ie":
                    if (System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("You are operating Mac OS which doesn't support Internet Explorer");
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;

                case "safari":
                    if (System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("You are operating Windows OS which doesn't support Safari");
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver = new SafariDriver();
                    break;

                case "browserStack":
                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setCapability("os", "Windows");
                    caps.setCapability("os_version", "10");
                    caps.setCapability("browser", "Chrome");
                    caps.setCapability("browser_version", "latest");
                    caps.setCapability("name", "Book_Movie_As_Logged_User(");
                    caps.setCapability("resolution", "1920x1080");
                    caps.setCapability("project", "ReelCinemas ");
                    caps.setCapability("browserstack.selenium_version", "3.141.0");
                    caps.setCapability("browserstack.networkLogs", "true");
                    caps.setCapability("browserstack.selenium_version", "3.141.59");
                    driver = new RemoteWebDriver(new URL(URL), caps);

            }
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

//    public static void closeDriver() {
//        if (driver != null) {
//            driver.quit();
//            driver = null;
//        }
//    }
}