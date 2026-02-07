package admin.api.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        // Point to the root features folder to avoid path errors
        features = "src/test/resources/features",
        glue = {
                "admin.ui.stepdefinitions",
                "admin.api.stepdefinitions",
                "common.hooks"
        },
        plugin = {"pretty", "html:target/api-report.html"},
        tags = "@CategoryManagement"
)
public class AdminApiTestRunner {
}