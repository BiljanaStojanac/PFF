package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.sql.Driver;

public class HomePage {

    WebDriver driver;

public HomePage(WebDriver driver)
{
    this.driver = driver;
}

    private By search = By.id("twotabsearchtextbox");


    public void enterSearchText(String text){

        WebElement element = driver.findElement(search);
        element.sendKeys(text);
        element.submit();


    }


}
