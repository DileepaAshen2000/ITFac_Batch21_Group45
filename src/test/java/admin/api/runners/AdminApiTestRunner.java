package admin.api.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/admin/api",
        glue = {"admin.api.stepdefinitions", "common.utils"},
        plugin = {"pretty", "html:target/cucumber-reports/admin-api.html"},
        monochrome = true
)
public class AdminApiTestRunner {
}
