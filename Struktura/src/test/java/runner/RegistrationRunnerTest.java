package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/registration.feature",
        plugin = "pretty",
        glue = {"setup.driver",
                "keywordsAndSteps/registration"})

public class RegistrationRunnerTest {

}
