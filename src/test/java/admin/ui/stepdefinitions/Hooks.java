package admin.ui.stepdefinitions;

import common.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.Alert;

public class Hooks {

    @Before
    public void setup() {
        DriverFactory.getDriver();
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @After
    public void cleanupAlerts() {
        try {
            Alert a = DriverFactory.getDriver().switchTo().alert();
            a.dismiss();
        } catch (Exception ignored) {
            // no alert open
        }
    }

}
