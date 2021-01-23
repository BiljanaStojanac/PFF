package keywordsAndSteps.registration;

import base.BasePage;

import java.util.List;

public class RegistrationKeywords extends BasePage {



    //TC1
    public void checkIfDashboardIsLoaded(){
        List<String> stepErrors = RegistrationSteps.currentStep.getErrors();
        isDisplayedElementXpath(stepErrors,"Sign Up", "//a[contains(text(),'Log in/Sign Up')]");
    }

    public void clickOnSignUpOnDashboard(){
        List<String> stepErrors = RegistrationSteps.currentStep.getErrors();
        clickElementXpath(stepErrors,"Sign Up Dashboard", "//a[contains(text(),'Log in/Sign Up')]");
    }

    public void clickOnSignUpOnLists(){
        List<String> stepErrors = RegistrationSteps.currentStep.getErrors();
        clickElementXpath(stepErrors,"Sign Up Lists", "//div[.=' Sign up ']");
    }


    public void checkRedirectionToSignUp(){
        List<String> stepErrors = RegistrationSteps.currentStep.getErrors();
        isDisplayedElementXpath(stepErrors, "Sign up overlay","//span[@class='ng-star-inserted']");
    }
}
