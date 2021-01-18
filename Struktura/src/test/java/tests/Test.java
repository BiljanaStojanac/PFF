package tests;

import cucumber.api.CucumberOptions;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import keywordsAndSteps.test1.Test1Steps;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import setup.BasePage;
import setup.driver.Setup;
import utilities.Driver;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class Test  {

    WebDriver driver;
    HomePage homePage;

    @Given("^Go to amazon$")
    public void go_to_amazon() throws Throwable {

        driver = Driver.get();
        driver.get("https://www.amazon.com/");

    }

    @When("^Amazon is loaded wait$")
    public void amazon_is_loaded_wait() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //waitIsPresentId("twotabsearchtextbox");

    }

    @Then("^Enter asd in search$")
    public void enter_asd_in_search() throws Throwable {
        homePage = new HomePage(driver);
        homePage.enterSearchText("asd");


    }




}
