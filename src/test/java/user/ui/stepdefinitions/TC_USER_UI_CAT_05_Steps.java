package user.ui.stepdefinitions;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

import common.utils.DriverFactory;
import user.ui.pages.UserCategoryListPage;
import user.ui.pages.UserLoginPage;

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

        boolean hasRows = categoryPage.hasTableRows();
        boolean hasMessage = categoryPage.isNoCategoryMessageDisplayed();

        if (hasRows) {

            assertTrue("Table has data, message should not appear", !hasMessage);
        } else {

            assertTrue("No data but message not shown", hasMessage);
        }
    }

    @Then("Message text should contain {string}")
    public void message_text_should_contain(String text) {
        if (categoryPage.isNoCategoryMessageDisplayed()) {
            assertTrue(categoryPage.getNoCategoryMessageText().contains(text));
        }
    }
}
