package user.ui.stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.*;
import user.ui.pages.UserCategoryListPage;
import user.ui.pages.UserLoginPage;
import common.utils.DriverFactory;

public class TC_USER_UI_CAT_04_Steps {

    WebDriver driver = DriverFactory.getDriver();
    UserLoginPage loginPage;
    UserCategoryListPage categoryPage;

    int initialPageNumber;
    int pageAfterNext;
    int pageAfterPrevious;

    List<Integer> categoriesOnPage1;
    List<Integer> categoriesOnPage2;

    @Given("User is logged in and multiple pages of categories exist")
    public void user_is_logged_in_and_multiple_pages_exist() {
        driver.get("http://localhost:8008/ui/login");

        loginPage = new UserLoginPage(driver);
        loginPage.loginAsUser("testuser", "test123");
    }

    @When("User navigates to the category list")
    public void user_navigates_to_category_list() {
        driver.get("http://localhost:8008/ui/categories");
        categoryPage = new UserCategoryListPage(driver);

        // Verify pagination exists
        assertTrue(
                "Pagination is not visible",
                categoryPage.isPaginationVisible()
        );

        initialPageNumber = categoryPage.getCurrentPageNumber();
        categoriesOnPage1 = categoryPage.getCategoryIds();
    }

    @When("User clicks the Next button")
    public void user_clicks_next_button() {
        categoryPage.clickNextButton();
        pageAfterNext = categoryPage.getCurrentPageNumber();
        categoriesOnPage2 = categoryPage.getCategoryIds();
    }

    @When("User clicks the Previous button")
    public void user_clicks_previous_button() {
        categoryPage.clickPreviousButton();
        pageAfterPrevious = categoryPage.getCurrentPageNumber();
    }

    @Then("Table updates to show next page and current page is highlighted")
    public void table_updates_to_next_page() {
        assertEquals(
                "Page number did not increase after clicking Next",
                initialPageNumber + 1,
                pageAfterNext
        );

        assertNotEquals(
                "Categories did not change after clicking Next",
                categoriesOnPage1,
                categoriesOnPage2
        );
    }

    @Then("Table updates to show previous page and current page is highlighted")
    public void table_updates_to_previous_page() {
        assertEquals(
                "Page number did not return to initial after clicking Previous",
                initialPageNumber,
                pageAfterPrevious
        );

        List<Integer> currentCategories = categoryPage.getCategoryIds();
        assertEquals(
                "Categories did not return to page 1 after clicking Previous",
                categoriesOnPage1,
                currentCategories
        );
    }
}