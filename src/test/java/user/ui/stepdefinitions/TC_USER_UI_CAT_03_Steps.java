package user.ui.stepdefinitions;

import static org.junit.Assert.assertFalse;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.*;
import user.ui.pages.UserCategoryListPage;
import user.ui.pages.UserLoginPage;
import common.utils.DriverFactory;

public class TC_USER_UI_CAT_03_Steps {

    WebDriver driver = DriverFactory.getDriver();
    UserLoginPage loginPage;
    UserCategoryListPage categoryPage;

    @Given("User is logged in and categories exist")
    public void user_is_logged_in_and_categories_exist() {
        driver.get("http://localhost:8008/ui/login");

        loginPage = new UserLoginPage(driver);
        loginPage.loginAsUser("testuser", "test123");
    }

    @When("User views the categories list")
    public void user_views_the_categories_list() {
        driver.get("http://localhost:8008/ui/categories");
        categoryPage = new UserCategoryListPage(driver);
    }

    @Then("No edit or delete actions should be available")
    public void no_edit_or_delete_actions_should_be_available() {

        assertFalse(
                "Enabled edit buttons are visible for User",
                categoryPage.hasEnabledEditButtons()
        );

        assertFalse(
                "Enabled delete buttons are visible for User",
                categoryPage.hasEnabledDeleteButtons()
        );
    }
}