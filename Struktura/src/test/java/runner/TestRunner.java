package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/test.feature",
		strict = false,
		plugin = "pretty",
		glue = "tests")
/*glue = {"setup.driver",
                "keywordsAndSteps/test1"})*/
public class TestRunner {

}
