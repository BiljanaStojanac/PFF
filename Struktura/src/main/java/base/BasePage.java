package base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.driver.Setup;

import java.util.List;

public class BasePage extends StepHandlers {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = Setup.driver;
        wait = new WebDriverWait(this.driver, 5);
    }

    protected Boolean waitIsGone(String elementXPath) {
        try {
            return wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(elementXPath))));
        } catch (Exception e) {
            return null;
        }
    }

    protected WebElement waitIsClickable(String elementXPath) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXPath)));
        } catch (Exception e) {
            return null;
        }
    }

    protected WebElement waitIsPresent(String elementXPath) {

        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXPath)));
        } catch (Exception e) {
            System.err.println("Couldn't find " + elementXPath);
            return null;
        }
    }

    protected WebElement waitIsPresentInElement(String elementXPath, WebElement parent) {

        try {
            return parent.findElement(By.xpath(elementXPath));
        } catch (Exception e) {
            System.err.println("Couldn't find " + elementXPath);
            return null;
        }
    }

    protected List<WebElement> waitArePresent(String elementXPath) {

        try {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(elementXPath)));
        } catch (Exception e) {
            return null;
        }
    }

    protected boolean isDisplayedElement(WebElement element, List<String> stepErrors, String elementName, String elementXPath) {
        if (element.isDisplayed()) {
            return true;
        } else {
            stepErrors.add(notDisplayed(elementName, elementXPath));
            return false;
        }
    }


    //Click element ID

    /**
     * @param elementId is used to locate desired element
     * @return will wait until the desired element is present on the page and it will check every 0.5 sec for 15sec
     */
    protected WebElement waitIsPresentId(String elementId) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementId)));
        } catch (Exception e) {
            System.err.println("Couldn't find " + elementId);
            return null;
        }
    }

    /**
     * @param elementName is used to find and locate desired element more easily.
     * @param elementID   is used to identify which element is not present on the page.
     * @return will return String that will state, that the desired element is not present on the page at given time.
     */

    String notPresentID(String elementName, String elementID) {
        String screenShotPath = getScreenShot(elementName);
        return "Element with name: " + elementName + " ID: " + elementID + " is not present.\n" + screenShotPath;
    }

    /**
     * @param stepErrors  will collect all errors and add them to the list of errors.
     * @param elementName is used to find and locate desired element more easily.
     * @param elementID   is used to identify the desired element on the page and check if it's displayed.
     * @return will return boolean value based on result.
     */
    protected boolean isPresentID(List<String> stepErrors, String elementName, String elementID) {
        if (waitIsPresentId(elementID) == null) {
            stepErrors.add(notPresentID(elementName, elementID));
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param elementName is used to find and locate desired element more easily.
     * @param elementID   is used to identify which element is not displayed on the page.
     * @return will return String that will state, that the desired element is not displayed on the page at given time.
     */
    String notDisplayedID(String elementName, String elementID) {
        String screenShotPath = getScreenShot(elementName);
        return "Element with name: " + elementName + " ID: " + elementID + " is not displayed.\n" + screenShotPath;
    }

    /**
     * @param stepErrors  will collect all errors and add them to the list of errors.
     * @param elementName is used to find and locate desired element more easily.
     * @param elementID   is used to identify the element that you wish to check if it's displayed on the page.
     * @return Depending on the presence of the element on the page, the return statement will:
     * - if true: Check if given element is displayed on the page.
     * - if false:
     */
    protected boolean isDisplayedElementID(List<String> stepErrors, String elementName, String elementID) {
        if (isPresentID(stepErrors, elementName, elementID)) {
            if (!driver.findElement(By.id(elementID)).isDisplayed()) {
                stepErrors.add(notDisplayedID(elementName, elementID));
                return false;
            }
        }
        return true;
    }

    /**
     * @param elementName is used to more easily find desired element.
     * @param elementID   is used to target desired element on the page.
     * @return will return String type error message that will state that desired element couldn't be clicked.
     */
    String notClickableID(String elementName, String elementID) {
        String screenShotPath = getScreenShot(elementName);
        return "Element with name: " + elementName + " and ID: " + elementID + " is not clickable.\n" + screenShotPath;
    }

    /**
     * @param stepErrors  will collect all errors and add them to the list of errors.
     * @param elementName is used to easily locate desired element.
     * @param elementID   ID of the desired element you wish to check if it's clickable.
     * @return will check if the given element is clickable on the page for 15 seconds in interval of 0.5 sec.
     */
    protected WebElement waitIsClickableID(List<String> stepErrors, String elementName, String elementID) {

        try {
            return wait.until(ExpectedConditions.elementToBeClickable(By.id(elementID)));
        } catch (Exception e) {
            stepErrors.add(notClickableID(elementName, elementID));
            return null;
        }
    }

    /**
     * @param stepErrors  this param is used to return error into the logger
     * @param elementName element name is used to find targeted element easily
     * @param elementID   ID of the desired element you wish to click
     * @return The point of this function is to check if the element is present on the page,
     * displayed on the page, and if it is, click on the given element.
     */
    protected void clickElementID(List<String> stepErrors, String elementName, String elementID) {
        if (isDisplayedElementID(stepErrors, elementName, elementID)) {
            if (waitIsClickableID(stepErrors, elementName, elementID) != null) {
                waitIsClickableID(stepErrors, elementName, elementID).click();
            }
        }
    }

//______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________

    // Click element Xpath

    /**
     * @param elementXPath xpath of the desired element you wish to check if it's present
     * @return The function will constantly check during 15 seconds if the given element is present on the page
     */

    protected WebElement waitIsPresentXpath(String elementXPath) {

        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXPath)));
        } catch (Exception e) {
            System.err.println("Couldn't find " + elementXPath);
            return null;
        }
    }

    /**
     * @param elementName  element name is used to find targeted element easily
     * @param elementXPath xpath of the desired element you wish to check if it's present on the page
     * @return The function will return String with an message saying that the desired element is not present on the page.
     * It will also capture the screenshot of the page at that moment for validation.
     */

    String notPresentXpath(String elementName, String elementXPath) {
        String screenShotPath = getScreenShot(elementName);
        return "Element with name: " + elementName + " xPath: " + elementXPath + " is not present.\n" + screenShotPath;
    }

    /**
     * @param stepErrors   this param is used to return error into the logger
     * @param elementXPath xpath of the desired element you wish to check if it's present on the page
     * @param elementName  element name is used to find targeted element easily
     * @return The function will check if the given element is present on the page, and if it's not,
     * it will return error with a screenshot.
     */

    protected boolean isPresentXpath(List<String> stepErrors, String elementName, String elementXPath) {
        if (waitIsPresentXpath(elementXPath) == null) {
            stepErrors.add(notPresentXpath(elementName, elementXPath));
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param elementName  element name is used to find targeted element easily
     * @param elementXPath xpath of the desired element you wish to check if it's displayed on the page
     * @return The function will return String with an message saying that the desired element is not displayed on the page.
     * It will also capture the screenshot of the page at that moment for validation.
     */

    String notDisplayedXpath(String elementName, String elementXPath) {
        String screenShotPath = getScreenShot(elementName);
        return "Element with name: " + elementName + " xPath: " + elementXPath + " is not displayed.\n" + screenShotPath;
    }

    /**
     * @param stepErrors   this param is used to return error into the logger
     * @param elementName  element name is used to find targeted element easily
     * @param elementXPath xpath of the desired element you wish to check if it's displayed on the page
     * @return The function will first check if the given element is present on the page, and if it is,
     * then it will check if the given element is displayed on the page at the moment.
     */

    protected boolean isDisplayedElementXpath(List<String> stepErrors, String elementName, String elementXPath) {
        if (isPresentXpath(stepErrors, elementName, elementXPath)) {
            if (!driver.findElement(By.xpath(elementXPath)).isDisplayed()) {
                stepErrors.add(notDisplayedXpath(elementName, elementXPath));
                return false;
            }
        }
        return true;
    }

    /**
     * @param elementXpath xpath of the desired element you wish to check if it's clickable
     * @return The function will constantly check during 15 seconds if the given element is clickable
     */

    protected WebElement waitIsClickableXpath(List<String> stepErrors, String elementName, String elementXpath) {
        if (driver.findElement(By.xpath(elementXpath)) != null) {
            return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
        } else {
            stepErrors.add(customError(elementName, "Given element is not clickable at the moment", stepErrors));
            return null;
        }
    }

    /**
     * @param stepErrors   this param is used to return error into the logger
     * @param elementName  element name is used to find targeted element easily
     * @param elementXpath xpath of the desired element you wish to click
     * @return The function will check if the element is present on the page,
     * displayed on the page, and if it is, click on the given element.
     */

    protected void clickElementXpath(List<String> stepErrors, String elementName, String elementXpath) {
        if (isDisplayedElementXpath(stepErrors, elementName, elementXpath)) {
            if (waitIsClickableXpath(stepErrors, elementName, elementXpath) != null) {
                waitIsClickableXpath(stepErrors, elementName, elementXpath).click();
            }
        }
    }

//______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________


    String notPresent(String elementName, String elementXPath) {
        String screenShotPath = getScreenShot(elementName);
        return "Element with name: " + elementName + " xPath: " + elementXPath + " is not present.\n" + screenShotPath;
    }

    String notDisplayed(String elementName, String elementXPath) {
        String screenShotPath = getScreenShot(elementName);
        return "Element with name: " + elementName + " xPath: " + elementXPath + " is not displayed.\n" + screenShotPath;
    }

    protected void isEqualValues(String actualValue, String expectedValue, List<String> stepErrors, String nameOfAttribute) {
        if (!actualValue.equals(expectedValue)) {
            String screenShotPath = getScreenShot(nameOfAttribute);
            stepErrors.add("Element/Attribute:  " + nameOfAttribute + " actual have value [ " + actualValue + " ] but it should have value [ " + expectedValue + " ]\n" +
                    screenShotPath);
        }
    }

    protected static String makeXPath(String testContext, String testContainer, String testType, String testName) {
        if (testName.equals("")) {
            return "//*[@test-context='" + testContext + "' and @test-container='" + testContainer + "' and @test-type='" + testType + "']";
        } else if (testType.equals("")) {
            return "//*[@test-context='" + testContext + "' and @test-container='" + testContainer + "' and @test-name='" + testName + "']";
        } else if (testContainer.equals("")) {
            return "//*[@test-context='" + testContext + "'and @test-type='" + testType + "' and @test-name='" + testName + "']";
        } else if (testContext.equals("")) {
            return "//*[@test-container='" + testContainer + "'and @test-type='" + testType + "' and @test-name='" + testName + "']";
        } else {
            return "//*[@test-context='" + testContext + "' and @test-container='" + testContainer + "' and @test-type='" + testType + "' and @test-name='" + testName + "']";
        }
    }

    protected static String makeXPathWithSpecificChild(String testContext, String testContainer, String testType, String testName, String childElementClass) {
        if (testName.equals("")) {
            return "//*[@test-context='" + testContext + "' and @test-container='" + testContainer + "' and @test-type='" + testType + "']//" + childElementClass;
        } else if (testType.equals("")) {
            return "//*[@test-context='" + testContext + "' and @test-container='" + testContainer + "' and @test-name='" + testName + "']//" + childElementClass;
        } else if (testContainer.equals("")) {
            return "//*[@test-context='" + testContext + "'and @test-type='" + testType + "' and @test-name='" + testName + "']//" + childElementClass;
        } else if (testContext.equals("")) {
            return "//*[@test-container='" + testContainer + "' and @test-type='" + testType + "' and @test-name='" + testName + "']//" + childElementClass;
        } else {
            return "//*[@test-context='" + testContext + "' and @test-container='" + testContainer + "' and @test-type='" + testType + "' and @test-name='" + testName + "']//" + childElementClass;
        }
    }


    public String getScreenShot(String elementName) {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        return "Screenshot: <img src=\"data:image/png;base64," + scrShot.getScreenshotAs(OutputType.BASE64) + "\" style=\"width:350px; height:auto\"><br>" + elementName + "</img>";
    }


    protected void isValueContain(String actualValue, String expectedValue, List<String> stepErrors, String nameOfAttribute) {
        if (!actualValue.contains(expectedValue)) {
            String screenShotPath = getScreenShot(nameOfAttribute);
            stepErrors.add("Element/Attribute:  " + nameOfAttribute + " actual have value [ " + actualValue + " ] but it should contain value [ " + expectedValue + " ]\n" + screenShotPath);
        }
    }

    protected void isTextEqual(String actualText, String expectedText, List<String> stepErrors, String nameOfElement) {
        if (!actualText.equals(expectedText)) {
            String screenShotPath = getScreenShot(nameOfElement);
            stepErrors.add("Element:  " + nameOfElement + " actual have text [ " + actualText + " ] but it should have text [ " + expectedText + " ]\n" + screenShotPath);
        }
    }

    protected void isTextEqualIgnoreCase(String actualText, String expectedText, List<String> stepErrors, String nameOfElement) {
        if (!actualText.equalsIgnoreCase(expectedText)) {
            String screenShotPath = getScreenShot(nameOfElement);
            stepErrors.add("Element:  " + nameOfElement + " actual have text [ " + actualText + " ] but it should have text [ " + expectedText + " ]\n" + screenShotPath);
        }
    }


    protected String customError(String elementName, String message, List<String> stepErrors) {
        return elementName + " | " + message + "<br>" + getScreenShot(elementName);
    }

    //Wait till LISTS
    protected List<WebElement> waitIsPresentListById(String elementId) {

        try {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(elementId)));
        } catch (Exception e) {
            System.err.println("Couldn't find list of elements with ID: " + elementId);
            return null;
        }
    }

    protected List<WebElement> waitIsPresentListByXpath(String elementXpath) {

        try {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(elementXpath)));
        } catch (Exception e) {
            System.err.println("Couldn't find list of elements with Xpath: " + elementXpath);
            return null;
        }
    }

    protected List<WebElement> waitIsPresentListByClassName(String elementClassName) {

        try {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(elementClassName)));
        } catch (Exception e) {
            System.err.println("Couldn't find list of elements with ClassName: " + elementClassName);
            return null;
        }
    }
}
