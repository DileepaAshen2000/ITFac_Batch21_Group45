package admin.ui.stepdefinitions;

import common.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setup() {
        System.out.println(">>> SETTING UP DRIVER <<<");
        DriverFactory.getDriver(); // initialize driver
    }

    @After
    public void tearDown() {
        System.out.println(">>> QUITTING DRIVER <<<");
        DriverFactory.quitDriver();
    }
}
