package user.api.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/user/api",
        glue = "user.api.stepdefinitions",
        plugin = {
                "pretty",
                "html:target/user-api-report.html"
        },
        monochrome = true
)
public class UserApiTestRunner {
}
