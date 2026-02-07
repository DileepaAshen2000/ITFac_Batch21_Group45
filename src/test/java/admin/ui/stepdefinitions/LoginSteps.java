package admin.ui.stepdefinitions;

import admin.ui.pages.LoginPage;
import io.cucumber.java.en.Given;

public class LoginSteps {

    LoginPage loginPage = new LoginPage();

    @Given("Admin is logged into the system")
    public void admin_is_logged_in() {
        System.out.println(">>> LOGIN STEP EXECUTED <<<");
        loginPage.login("admin", "admin123");
    }
}
