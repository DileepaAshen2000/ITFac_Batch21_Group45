package user.ui.stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import user.ui.pages.UserCategoryListPage;
import user.ui.pages.UserLoginPage;
import common.utils.DriverFactory;

import static org.junit.Assert.assertFalse;

public class TC_USER_UI_CAT_03_Steps {

    WebDriver driver = DriverFactory.getDriver();
    UserLoginPage loginPage;
    UserCategoryListPage categoryPage;

    @Given("User is logged into the system as an user")
    public void user_is_logged_in_as_user() {
        loginPage = new UserLoginPage(driver);
        loginPage.openLoginPage();
        loginPage.loginAsUser("testuser", "test123");
    }

    @When("User navigates to the category list page")
    public void user_navigates_to_the_category_list_page() {
        driver.get("http://localhost:8008/ui/categories");
        categoryPage = new UserCategoryListPage(driver);
    }

    @Then("The Delete butto not visible in the actions column")
    public void verify_delete_button_not_visible() {
        boolean isVisible = categoryPage.isDeleteOptionVisible();

        assertFalse(
                "Security issue: Delete button is visible for normal user!",
                isVisible
        );
    }
}
