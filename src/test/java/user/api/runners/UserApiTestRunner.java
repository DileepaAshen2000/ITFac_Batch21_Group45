package user.api.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/user/api",
        glue = {"user.api.stepdefinitions", "common.utils"},
        tags = "@UserApi and not @ignore",
        plugin = {"pretty", "html:target/cucumber-reports/user-api.html"},
        monochrome = true
)
public class UserApiTestRunner {
}
