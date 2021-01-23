package runner;

import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/registration.feature",
        strict = false,
        plugin = "pretty",
        glue = {"setup.driver",
                "keywordsAndSteps/registration"})

public class RegistrationRunner {

}
