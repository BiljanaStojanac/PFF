package tests;

import base.BasePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

import java.util.concurrent.TimeUnit;


public class Test extends BasePage {


    HomePage homePage;

    @Given("^Go to amazon$")
    public void go_to_amazon() throws Throwable {


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
