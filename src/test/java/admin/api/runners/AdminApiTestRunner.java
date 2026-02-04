package admin.api.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/admin/api",
        glue = "admin.api.stepdefinitions",
        plugin = {"pretty", "html:target/admin-api-report.html"},
        monochrome = true
)
public class AdminApiTestRunner {
}
