package user.ui.stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import user.ui.pages.UserCategoryListPage;
import user.ui.pages.UserLoginPage;
import common.utils.DriverFactory;
import static org.junit.Assert.assertFalse;

public class TC_USER_UI_CAT_04_Steps {

    WebDriver driver = DriverFactory.getDriver();
    UserLoginPage loginPage;
    UserCategoryListPage categoryPage;

    @Given("User is logged into the system as a standard user")
    public void user_is_logged_in() {
        loginPage = new UserLoginPage(driver);
        loginPage.openLoginPage();
        loginPage.loginAsUser("testuser", "test123");
    }

    @When("User navigates to the category list page")
    public void user_navigates_to_categories() {
        driver.get("http://localhost:8008/ui/categories");
        categoryPage = new UserCategoryListPage(driver);
    }

    @Then("The Edit category option should not be displayed in the actions column")
    public void verify_edit_option_not_displayed() {
        boolean isVisible = categoryPage.isEditOptionVisible();
        assertFalse("Security Risk: Edit button is visible to a standard user!", isVisible);
    }
}