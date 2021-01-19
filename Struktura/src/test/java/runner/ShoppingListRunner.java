package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/shoppingList.feature",
		strict = false,
		plugin = "pretty",
		glue = "keywordsAndSteps")
/*glue = {"setup.driver",
                "keywordsAndSteps/test1"})*/
public class ShoppingListRunner {

}
