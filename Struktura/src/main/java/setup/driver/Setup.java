package setup.driver;

import io.cucumber.java.Before;
import utilities.ConfigurationReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import setup.driver.custom.ChromeDriverWithExceptions;
import setup.logger.LoggerController;

import java.net.MalformedURLException;
import java.net.URL;

public class Setup {

    public static WebDriver driver;
    public static final String remoteWebDriverUsername = "markomirtic2";
    public static final String remoteWebDriverAccessKey = "P1vgaPzT4btgU1cLjkrg";
    public static final String browserstackURL = "https://" + remoteWebDriverUsername + ":" + remoteWebDriverAccessKey + "@hub-cloud.browserstack.com/wd/hub";

    public void setInfo() {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName();
        LoggerController.INSTANCE.setTestingBrowser(browserName);
        String os = cap.getPlatform().toString();
        LoggerController.INSTANCE.setOs(os);
        String v = cap.getVersion().toString();
        LoggerController.INSTANCE.setBrowserVersion(v);

//        String appVersionXpath = makeXPath("version", "footer", "text", "currentVersionAndBuild");
//        WebElement appVersion = waitIsPresent(appVersionXpath);
//        String appVersionString = appVersion.getText();
//        LoggerController.INSTANCE.setWebAppVersion(appVersionString);
    }

    @Before
    public void setupDriver() throws MalformedURLException {

        BasicConfigurator.configure();
        String browser = ConfigurationReader.get("browser");
        String url = ConfigurationReader.get("url");
        System.out.println("browser = " + browser);
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                //System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
                driver = new ChromeDriverWithExceptions();
                driver.manage().window().maximize();
                driver.get(url);
                setInfo();
                break;

            case "chromeHeadless":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                setInfo();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
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
                driver.manage().window().maximize();
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
                driver = new RemoteWebDriver(new URL(browserstackURL), caps);
                driver  = new Augmenter().augment( driver );
                //( (TakesScreenshot)driver ).getScreenshotAs( ... );
                break;
        }
    }
}
