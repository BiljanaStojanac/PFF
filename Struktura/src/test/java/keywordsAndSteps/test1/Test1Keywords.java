package keywordsAndSteps.test1;

import org.openqa.selenium.*;
import base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class Test1Keywords extends BasePage {


    private WebElement contextParentElement;

    public List<String> screenShot() {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        try {
            String toAdd = "<strong>Current url:" + driver.getCurrentUrl() + "</strong> Screenshot: <img src=\"data:image/png;base64," + contextParentElement.getScreenshotAs(OutputType.BASE64) + "\" style=\"width:850px; height:auto\"><br>" + "</img>";
            List<String> validImages = new ArrayList<>();
            validImages.add(toAdd);
            return validImages;
        } catch (Exception e) {
            String toAdd = "<strong>Current url:" + driver.getCurrentUrl() + "</strong> Screenshot: <img src=\"data:image/png;base64," + scrShot.getScreenshotAs(OutputType.BASE64) + "\" style=\"width:850px; height:auto\"><br>" + "</img>";
            List<String> validImages = new ArrayList<>();
            validImages.add(toAdd);
            return validImages;
        }
    }

    public void validImage() {
        Test1Steps.currentStep.setValidImages(screenShot());
    }


    //Test 1

    public void goToGoogle() {
        // driver.get("https://www.amazon.com/");
    }

    public void checkIfGoogleIsLoaded() {

        List<String> stepErrors = Test1Steps.currentStep.getErrors();
        isDisplayedElementID(stepErrors, "Test", "twotabsearchtextbox");
    }

    public void inputInSearch() {
        List<String> stepErrors = Test1Steps.currentStep.getErrors();
        isDisplayedElementID(stepErrors, "search bar", "twotabsearchtesxtbox");

        clickElementID(stepErrors, "Search", "nav-search-submit-text");


    }

    public void goToFacebookPage(){
        List<String> stepErrors = Test1Steps.currentStep.getErrors();
        driver.get("https://www.facebook.com");
    }
}
