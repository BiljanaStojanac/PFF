package keywordsAndSteps.registration;

import base.BasePage;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;

import java.lang.reflect.Method;
import java.time.Instant;

public class RegistrationSteps extends BasePage {

    public final RegistrationKeywords registrationKeywords;
    Instant startedTestCaseTime;

    public RegistrationSteps() {
        setSection("Registration");
        registrationKeywords = new RegistrationKeywords();
    }

    @Before
    public void getStoryName(Scenario scenario) {
        currentTestCaseName = scenario.getName();
        //startedTestCaseTime = Instant.now();
    }

    @Given("User is on dashboard")
    public void userIsOnDashboard() {
        makeTestCase();
        Method callingMethod = new Object() {
        }.getClass().getEnclosingMethod();
        createStep(callingMethod, false);
        this.registrationKeywords.checkIfDashboardIsLoaded();
        addCurrentStepToTestCase();
    }

    @When("User clicks sign up on dashboard")
    public void userClicksSignUpOnDashboard() {
        Method callingMethod = new Object() {
        }.getClass().getEnclosingMethod();
        createStep(callingMethod, false);
        this.registrationKeywords.clickOnSignUpOnDashboard();
        addCurrentStepToTestCase();
    }

    @And("User clicks sign up on lists page")
    public void userClicksSignUpOnListsPage() {
        Method callingMethod = new Object() {
        }.getClass().getEnclosingMethod();
        createStep(callingMethod, false);
        this.registrationKeywords.clickOnSignUpOnLists();
        addCurrentStepToTestCase();
    }

    @Then("User should be presented with overlay for signing up")
    public void userShouldBePresentedWithOverlayForSigningUp() {
        Method callingMethod = new Object() {
        }.getClass().getEnclosingMethod();
        createStep(callingMethod, true);
        this.registrationKeywords.checkRedirectionToSignUp();
        addCurrentStepToTestCase();
//        calculateForTestCase(startedTestCaseTime);
//        setExecutionTime();
        addTestCaseToSection("1");
        //System.out.println(currentTestCase.getWeight());
        createReport();
    }
}
