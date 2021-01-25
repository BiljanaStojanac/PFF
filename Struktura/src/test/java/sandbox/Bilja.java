package sandbox;

import base.BasePage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Bilja  extends BasePage{
    WebDriver driver;

    /*Login elementst*/
    private By btnLogInSingUp = By.className("login");

   @Before
    public void Start() throws IOException
    {

        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();

    }
    @Test
    public void test() throws IOException {

        driver.get("https://listonic.com/");
        driver.manage().window().maximize();
        driver.findElement(By.className("login")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS) ;
        driver.findElement(By.xpath("//a[normalize-space()='Sign In']")).click();




    }


}
