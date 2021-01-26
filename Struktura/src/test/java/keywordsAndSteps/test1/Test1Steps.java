package keywordsAndSteps.test1;

import base.BasePage;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import setup.logger.LoggerController;

import java.lang.reflect.Method;
import java.time.Instant;

public class Test1Steps extends BasePage {

    private final Test1Keywords test1Keywords;

    public Test1Steps() {
        setSection("Test");
        test1Keywords = new Test1Keywords();
    }

    Instant startedTestCaseTime;

    @Before
    public void getStoryName(Scenario scenario) {
        currentTestCaseName = scenario.getName();
        startedTestCaseTime = Instant.now();
    }

    @Given("Go to amazon d{int} f{int}")
    public void goToAmazonDF(int arg0, int arg1) {
        makeTestCase(arg0, arg1);
        Method callingMethod = new Object() {
        }.getClass().getEnclosingMethod();
        createStep(callingMethod, false);
        this.test1Keywords.goToGoogle();
        addCurrentStepToTestCase();
    }

    @When("Amazon is loaded")
    public void amazon_is_loaded() {
        Method callingMethod = new Object() {
        }.getClass().getEnclosingMethod();
        createStep(callingMethod, false);
        this.test1Keywords.checkIfGoogleIsLoaded();
        addCurrentStepToTestCase();
    }

    @Then("Enter asd in search")
    public void enter_asd_in_search() {
        Method callingMethod = new Object() {
        }.getClass().getEnclosingMethod();
        createStep(callingMethod, true);
        this.test1Keywords.inputInSearch();
        addCurrentStepToTestCase();
        calculateForTestCase(startedTestCaseTime);
        setExecutionTime();
        addTestCaseToSection("141-7");
        System.out.println(currentTestCase.getWeight());
        createReport();
    }

    @When("Go to facebook")
    public void goToFacebook() {
        Method callingMethod = new Object() {
        }.getClass().getEnclosingMethod();
        createStep(callingMethod, false);
        this.test1Keywords.goToFacebookPage();
        addCurrentStepToTestCase();

    }

    @Given("Test d{int} f{int}")
    public void testDF(int arg0, int arg1) {
        makeTestCase(arg0, arg1);
        Method callingMethod = new Object() {
        }.getClass().getEnclosingMethod();
        createStepWithNum1AndNum2(callingMethod, arg0, arg1, false);
        this.test1Keywords.goToGoogle();
        addCurrentStepToTestCase();
    }

    @Then("testing something")
    public void testingSomething() {
        Method callingMethod = new Object() {
        }.getClass().getEnclosingMethod();
        createStep(callingMethod, true);
        this.test1Keywords.inputInSearch();
        addCurrentStepToTestCase();
        calculateForTestCase(startedTestCaseTime);
        setExecutionTime();
        addTestCaseToSection("141-7");
        System.out.println(currentTestCase.getWeight());
        System.out.println(LoggerController.INSTANCE.getL1SectionR2().weightForAll());
        createReport();
    }
}
