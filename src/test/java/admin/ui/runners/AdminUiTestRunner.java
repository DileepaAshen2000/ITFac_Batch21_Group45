/*package admin.ui.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/admin",
        glue = {
                "admin.ui.stepdefinitions",
                "admin.ui.hooks"
        },
        tags = "@Admin",
        plugin = {
                "pretty",
                "html:target/admin-ui-report.html"
        },
        monochrome = true
)
public class AdminUiTestRunner {
}*/

package admin.ui.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/admin/ui",
        glue = {
                "admin.ui.stepdefinitions",
                "common.hooks"  // if you have setup/teardown hooks
        },
        plugin = {"pretty"},
        tags = "@TC_ADM_UI_CAT_06"
)
public class AdminUiTestRunner {
}
