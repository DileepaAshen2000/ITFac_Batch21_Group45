package user.ui.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/user",
        glue = {
                "user.ui.stepdefinitions",
                "user.ui.hooks"
        },
        tags = "@TC_USER_UI_CAT_01",
        plugin = {
                "pretty",
                "html:target/user-ui-report.html"
        },
        monochrome = true
)
public class UserUiTestRunner {
}
