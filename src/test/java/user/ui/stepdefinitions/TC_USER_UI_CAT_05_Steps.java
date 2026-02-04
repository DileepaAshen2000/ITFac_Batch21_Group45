package user.ui.stepdefinitions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.*;
import user.ui.pages.UserCategoryListPage;
import user.ui.pages.UserLoginPage;
import common.utils.DriverFactory;

public class TC_USER_UI_CAT_05_Steps {

    WebDriver driver = DriverFactory.getDriver();
    UserLoginPage loginPage;
    UserCategoryListPage categoryPage;

    @Given("User is logged in and no categories exist in the system")
    public void user_is_logged_in_and_no_categories_exist() {
        driver.get("http://localhost:8008/ui/login");

        loginPage = new UserLoginPage(driver);
        loginPage.loginAsUser("testuser", "test123");
    }

    @When("User navigates to empty categories page")
    public void user_navigates_to_empty_categories_page() {
        driver.get("http://localhost:8008/ui/categories");
        categoryPage = new UserCategoryListPage(driver);
    }

    @Then("No category found message should be displayed")
    public void no_category_found_message_should_be_displayed() {
        // Verify the "No category found" message is visible
        assertTrue(
                "No category found message is not displayed",
                categoryPage.isNoCategoryMessageDisplayed()
        );

        // Verify no table rows exist
        assertFalse(
                "Table rows are present when it should be empty",
                categoryPage.hasTableRows()
        );
    }

    @Then("Message text should contain {string}")
    public void message_text_should_contain(String expectedText) {
        String actualMessage = categoryPage.getNoCategoryMessageText();

        assertTrue(
                "Expected message to contain '" + expectedText + "' but got: " + actualMessage,
                actualMessage.toLowerCase().contains(expectedText.toLowerCase())
        );
    }
}