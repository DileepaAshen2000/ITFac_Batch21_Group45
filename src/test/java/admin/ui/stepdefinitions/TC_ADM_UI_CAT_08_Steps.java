package admin.ui.stepdefinitions;

import admin.ui.pages.CategoryListPage;
import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class TC_ADM_UI_CAT_08_Steps {

    // 1. Initialize variables immediately at the class level
    private WebDriver driver = DriverFactory.getDriver();
    private CategoryListPage categoryListPage = new CategoryListPage(driver);
    private int initialRowCount;

    @Given("Admin navigates to the category management list")
    public void admin_navigates_to_the_category_management_list() {
        // No need to initialize driver/page here anymore
        categoryListPage.navigateToCategoryList();
        initialRowCount = categoryListPage.getCategoryRowCount();
    }

    @When("Admin enters search term {string} in search box")
    public void admin_enters_search_term(String term) {
        categoryListPage.enterSearchTerm(term);
    }

    @And("Admin selects parent {string} from the dropdown")
    public void admin_selects_parent(String parentName) {
        categoryListPage.selectParentCategory(parentName);
    }

    @And("Admin clicks Search")
    public void admin_clicks_search() {
        categoryListPage.clickFilterSearchButton();
    }

    @Then("List updates to show only matching categories")
    public void list_updates() {
        int filteredCount = categoryListPage.getCategoryRowCount();
        Assert.assertTrue("Search results were not filtered", filteredCount <= initialRowCount);
    }

    @When("Admin clicks Reset")
    public void admin_clicks_reset() {
        // Now categoryListPage is guaranteed to be NOT null
        categoryListPage.clickResetButton();
    }

    @Then("List resets to show all categories")
    public void list_resets() {
        int currentCount = categoryListPage.getCategoryRowCount();
        Assert.assertEquals("List did not return to original size after reset", initialRowCount, currentCount);
    }
}