package base;

import setup.logger.LoggerController;
import setup.logger.LoggerStep;
import setup.logger.LoggerTestCase;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class StepHandlers {

    public static LoggerStep currentStep;
    public static LoggerTestCase currentTestCase;
    public String currentTestCaseName;

    public void setSection(String sectionName) {
        LoggerController.INSTANCE.getL1SectionR2().setNameOfSection(sectionName);
    }

    public void addCurrentStepToTestCase() {
        LoggerController.INSTANCE.addStepToCurrentTestCase(currentTestCase, currentStep);
    }

    public void addSkippedTestCaseToSection(String num, String reasonForSkipping) {
        currentTestCase.setNameOfTestCase(currentTestCaseName);
        currentTestCase.getFrequencyAndDamage();
        currentTestCase.setNumOfTestCase(num);
        //currentTestCase.setStatus();
        currentTestCase.setStatus(reasonForSkipping);
        LoggerController.INSTANCE.addToSection(currentTestCase);
    }

    public void addTestCaseToSection(String num) {
        currentTestCase.setNameOfTestCase(currentTestCaseName);
        //currentTestCase.getFrequencyAndDamage();
        currentTestCase.setNumOfTestCase(num);
        currentTestCase.setStatus();
        LoggerController.INSTANCE.addToSection(currentTestCase);
    }

    public void createReport() {
        LoggerController.INSTANCE.makeHtmlDocumentReportForCurrentSection();
    }


    public void stepName(String myAnnotation, boolean isExpected) {
        String firstSubString = myAnnotation.substring(myAnnotation.indexOf('='), myAnnotation.indexOf(")"));
        String name = firstSubString.substring(1, firstSubString.length());
        currentStep = new LoggerStep(name, isExpected);
        System.out.println(name);
    }

    public void createStep(Method callingMethod, boolean isExpected) {
        Annotation myAnnotationA = callingMethod.getAnnotations()[0];
        String myAnnotation = myAnnotationA.toString();
        stepName(myAnnotation, isExpected);
    }

    public void createStepWithString(Method callingMethod, String string, boolean isExpected) {
        Annotation myAnnotationA = callingMethod.getAnnotations()[0];
        String myAnnotation = myAnnotationA.toString();
        stepNameWithString(myAnnotation, string, isExpected);
    }

    public void stepNameWithString(String myAnnotation, String string, boolean isExpected) {
        String firstSubString = myAnnotation.substring(myAnnotation.indexOf('='), myAnnotation.indexOf(")"));
        String name = firstSubString.substring(10, firstSubString.length());
        name = name.replace("{string}", string);
        currentStep = new LoggerStep(name, isExpected);
        System.out.println(name);
    }

    public void createStepWithStringAndInt(Method callingMethod, String string, int num, boolean isExpected) {
        Annotation myAnnotationA = callingMethod.getAnnotations()[0];
        String myAnnotation = myAnnotationA.toString();
        stepNameWithStringAndNum(myAnnotation, string, num, isExpected);
    }

    public void stepNameWithStringAndNum(String myAnnotation, String string, int num, boolean isExpected) {
        String firstSubString = myAnnotation.substring(myAnnotation.indexOf('='), myAnnotation.indexOf(")"));
        String name = firstSubString.substring(10, firstSubString.length());
        name = name.replace("{int}", num + "");
        name = name.replace("{string}", string);
        System.out.println(name);
        currentStep = new LoggerStep(name, isExpected);
    }

    public void createStepWithNum(Method callingMethod, int num, boolean isExpected) {
        Annotation myAnnotationA = callingMethod.getAnnotations()[0];
        String myAnnotation = myAnnotationA.toString();
        stepNameWithNum(myAnnotation, num, isExpected);
    }

    public void stepNameWithNum(String myAnnotation, int num, boolean isExpected) {
        String firstSubString = myAnnotation.substring(myAnnotation.indexOf('='), myAnnotation.indexOf(")"));
        String name = firstSubString.substring(10, firstSubString.length());
        name = name.replace("{int}", num + "");
        System.out.println(name);
        currentStep = new LoggerStep(name, isExpected);
    }

    public void createStepWithNum1AndNum2(Method callingMethod, int num1, int num2, boolean isExpected) {
        Annotation myAnnotationA = callingMethod.getAnnotations()[0];
        String myAnnotation = myAnnotationA.toString();
        stepNameWithNum1AndNum2(myAnnotation, num1, num2, isExpected);
    }

    public void stepNameWithNum1AndNum2(String myAnnotation, int num1, int num2, boolean isExpected) {
        String firstSubString = myAnnotation.substring(myAnnotation.indexOf('='), myAnnotation.indexOf(")"));
        String name = firstSubString.substring(10, firstSubString.length());
        name = name.replace("{int}", num1 + "");
        name = name.replace("{int}", num1 + "");
        System.out.println(name);
        currentStep = new LoggerStep(name, isExpected);
    }

    public void makeTestCase(int damage, int frequency) {
        currentTestCase = new LoggerTestCase();
        // currentTestCase.setFrequency(frequency);
        // currentTestCase.setDamage(damage);
    }

    public void makeTestCase() {
        currentTestCase = new LoggerTestCase();
        // currentTestCase.setFrequency(frequency);
        // currentTestCase.setDamage(damage);
    }

    public void calculateForTestCase(Instant time) {
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(time, end);
        LoggerController.INSTANCE.getSumArray().add(timeElapsed.toMillis());
        System.out.println("Time taken for one test case: " + timeElapsed.toMillis() + " milliseconds");
    }

    public void setExecutionTime() {
        long sumOfAllExcTimeTC = LoggerController.INSTANCE.calculateSum();
        if (sumOfAllExcTimeTC > 60000) {
            long minutes = TimeUnit.MILLISECONDS.toMinutes(sumOfAllExcTimeTC);
            LoggerController.INSTANCE.setExecutionTime("<strong>Time taken for all test cases: " + minutes + " minutes</strong>");
        } else {
            long sec = TimeUnit.MILLISECONDS.toSeconds(sumOfAllExcTimeTC);
            LoggerController.INSTANCE.setExecutionTime("<strong>Time taken for all test cases: " + sec + " seconds</strong>");
        }
    }
}
