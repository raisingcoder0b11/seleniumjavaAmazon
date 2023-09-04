import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/java/Features",
        tags="@AmazonProductSearch",
        monochrome = true,
        plugin = {"json:target/destination/cucumber.json","html:target/cucumber/report.html"})



public class CucumberRunner {
}
