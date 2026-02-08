package user.ui.stepdefinitions;

import static org.junit.Assert.assertFalse;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.*;
import user.ui.pages.UserCategoryListPage;
import user.ui.pages.UserLoginPage;
import common.utils.DriverFactory;

public class TC_USER_UI_CAT_02_Steps {

    WebDriver driver = DriverFactory.getDriver();
    UserLoginPage loginPage;
    UserCategoryListPage categoryPage;

    @Given("User is logged in as regular user")
    public void user_is_logged_in_as_regular_user() {
        driver.get("http://localhost:8008/ui/login");

        loginPage = new UserLoginPage(driver);
        loginPage.loginAsUser("testuser", "test123");
    }

    @When("User navigates to categories page")
    public void user_navigates_to_categories_page() {
        driver.get("http://localhost:8008/ui/categories");
        categoryPage = new UserCategoryListPage(driver);
    }

    @Then("Add Category button should not be visible")
    public void add_category_button_should_not_be_visible() {
        assertFalse(
                "Add Category button is visible for User",
                categoryPage.isAddCategoryButtonVisible()
        );
    }
}
