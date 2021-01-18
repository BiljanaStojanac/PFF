package keywordsAndSteps.test1;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Test;
import setup.logger.LoggerController;
import setup.logger.LoggerSection;
import setup.logger.LoggerStep;
import setup.logger.LoggerTestCase;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Test1Steps {

    public static LoggerStep currentStep;
    public static LoggerTestCase currentTestCase;
    private String currentTestCaseName;

    private final Test1Keywords test1Keywords;


    private void setSection() {
        LoggerController.INSTANCE.getL1SectionR2().setNameOfSection("Test2");
    }

    public Test1Steps() {
        setSection();
        test1Keywords = new Test1Keywords();
    }

    private void addCurrentStepToTestCase() {
        LoggerController.INSTANCE.addStepToCurrentTestCase(currentTestCase, currentStep);
    }

    private void addSkippedTestCaseToSection(String num, String reasonForSkipping) {
        currentTestCase.setNameOfTestCase(currentTestCaseName);
        currentTestCase.getFrequencyAndDamage();
        currentTestCase.setNumOfTestCase(num);
        //currentTestCase.setStatus();
        currentTestCase.setStatus(reasonForSkipping);
        LoggerController.INSTANCE.addToSection(currentTestCase);
    }

    private void addTestCaseToSection(String num) {
        currentTestCase.setNameOfTestCase(currentTestCaseName);
        currentTestCase.getFrequencyAndDamage();
        currentTestCase.setNumOfTestCase(num);
        currentTestCase.setStatus();
        LoggerController.INSTANCE.addToSection(currentTestCase);
    }

    private void createReport() {
        LoggerController.INSTANCE.makeHtmlDocumentReportForCurrentSection();
    }


    private void stepName(String myAnnotation, boolean isExpected) {
        String firstSubString = myAnnotation.substring(myAnnotation.indexOf('='), myAnnotation.indexOf(")"));
        String name = firstSubString.substring(10, firstSubString.length());
        currentStep = new LoggerStep(name, isExpected);
        System.out.println(name);
    }

    private void createStep(Method callingMethod, boolean isExpected) {
        Annotation myAnnotationA = callingMethod.getAnnotations()[0];
        String myAnnotation = myAnnotationA.toString();
        stepName(myAnnotation, isExpected);
    }

    private void createStepWithString(Method callingMethod, String string, boolean isExpected) {
        Annotation myAnnotationA = callingMethod.getAnnotations()[0];
        String myAnnotation = myAnnotationA.toString();
        stepNameWithString(myAnnotation, string, isExpected);
    }

    private void stepNameWithString(String myAnnotation, String string, boolean isExpected) {
        String firstSubString = myAnnotation.substring(myAnnotation.indexOf('='), myAnnotation.indexOf(")"));
        String name = firstSubString.substring(10, firstSubString.length());
        name = name.replace("{string}", string);
        currentStep = new LoggerStep(name, isExpected);
        System.out.println(name);
    }

    private void createStepWithStringAndInt(Method callingMethod, String string, int num, boolean isExpected) {
        Annotation myAnnotationA = callingMethod.getAnnotations()[0];
        String myAnnotation = myAnnotationA.toString();
        stepNameWithStringAndNum(myAnnotation, string, num, isExpected);
    }

    private void stepNameWithStringAndNum(String myAnnotation, String string, int num, boolean isExpected) {
        String firstSubString = myAnnotation.substring(myAnnotation.indexOf('='), myAnnotation.indexOf(")"));
        String name = firstSubString.substring(10, firstSubString.length());
        name = name.replace("{int}", num + "");
        name = name.replace("{string}", string);
        System.out.println(name);
        currentStep = new LoggerStep(name, isExpected);
    }

    private void createStepWithNum(Method callingMethod, int num, boolean isExpected) {
        Annotation myAnnotationA = callingMethod.getAnnotations()[0];
        String myAnnotation = myAnnotationA.toString();
        stepNameWithNum(myAnnotation, num, isExpected);
    }

    private void stepNameWithNum(String myAnnotation, int num, boolean isExpected) {
        String firstSubString = myAnnotation.substring(myAnnotation.indexOf('='), myAnnotation.indexOf(")"));
        String name = firstSubString.substring(10, firstSubString.length());
        name = name.replace("{int}", num + "");
        System.out.println(name);
        currentStep = new LoggerStep(name, isExpected);
    }

    private void createStepWithNum1AndNum2(Method callingMethod, int num1, int num2, boolean isExpected) {
        Annotation myAnnotationA = callingMethod.getAnnotations()[0];
        String myAnnotation = myAnnotationA.toString();
        stepNameWithNum1AndNum2(myAnnotation, num1, num2, isExpected);
    }

    private void stepNameWithNum1AndNum2(String myAnnotation, int num1, int num2, boolean isExpected) {
        String firstSubString = myAnnotation.substring(myAnnotation.indexOf('='), myAnnotation.indexOf(")"));
        String name = firstSubString.substring(10, firstSubString.length());
        name = name.replace("{int}", num1 + "");
        name = name.replace("{int}", num1 + "");
        System.out.println(name);
        currentStep = new LoggerStep(name, isExpected);
    }

    private void makeTestCase(int damage, int frequency) {
        currentTestCase = new LoggerTestCase();
       // currentTestCase.setFrequency(frequency);
       // currentTestCase.setDamage(damage);

    }

    Instant startedTestCaseTime;

    @Before
    public void getStoryName(Scenario scenario) {
        currentTestCaseName = scenario.getName();
        startedTestCaseTime = Instant.now();
    }

    private void calculateForTestCase() {
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(startedTestCaseTime, end);
        LoggerController.INSTANCE.getSumArray().add(timeElapsed.toMillis());
        System.out.println("Time taken for one test case: " + timeElapsed.toMillis() + " milliseconds");
    }

    private void setExecutionTime() {
        long sumOfAllExcTimeTC = LoggerController.INSTANCE.calculateSum();
        if (sumOfAllExcTimeTC > 60000) {
            long minutes = TimeUnit.MILLISECONDS.toMinutes(sumOfAllExcTimeTC);
            LoggerController.INSTANCE.setExecutionTime("<strong>Time taken for all test cases: " + minutes + " minutes</strong>");
        } else {
            long sec = TimeUnit.MILLISECONDS.toSeconds(sumOfAllExcTimeTC);
            LoggerController.INSTANCE.setExecutionTime("<strong>Time taken for all test cases: " + sec + " seconds</strong>");
        }
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
        calculateForTestCase();
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
        calculateForTestCase();
        setExecutionTime();
        addTestCaseToSection("141-7");
        System.out.println(currentTestCase.getWeight());
        System.out.println(LoggerController.INSTANCE.getL1SectionR2().weightForAll());
        createReport();
    }
}
