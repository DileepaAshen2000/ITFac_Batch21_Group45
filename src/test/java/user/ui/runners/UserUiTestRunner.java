package user.ui.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/user/ui",
        glue = {
                "user.ui.stepdefinitions",
                "user.ui.hooks"
        },
        tags = "@User and not @ignore",
        plugin = {
                "pretty",
                "html:target/user-ui-report.html"
        },
        monochrome = true
)
public class UserUiTestRunner {
}
