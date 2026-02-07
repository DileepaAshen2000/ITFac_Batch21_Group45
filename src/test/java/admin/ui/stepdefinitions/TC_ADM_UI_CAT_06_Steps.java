package admin.ui.stepdefinitions;

import admin.ui.pages.CategoryListPage;
import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class TC_ADM_UI_CAT_06_Steps {

    private WebDriver driver;
    private CategoryListPage categoryListPage;
    private final String updatedCategoryName = "CAT6";

    @Given("Admin navigates to category list page")
    public void admin_navigates_to_category_list_page() {
        // Initialize driver and page object here to ensure the browser is ready
        this.driver = DriverFactory.getDriver();
        this.categoryListPage = new CategoryListPage(driver);

        categoryListPage.navigateToCategoryList();
        Assert.assertTrue("Category table was not displayed!", categoryListPage.isCategoryTableDisplayed());
    }

    @When("Admin clicks edit icon for an existing category")
    public void admin_clicks_edit_icon_for_a_category() {
        categoryListPage.clickEditForFirstCategory();
    }

    @And("Admin updates category name")
    public void admin_updates_the_category_name() {
        categoryListPage.updateCategoryName(updatedCategoryName);
    }

    @And("Admin clicks save button")
    public void admin_saves_the_category_changes() {
        categoryListPage.clickSaveButton();
    }

    @Then("Admin should be redirected to category list page")
    public void admin_should_be_redirected_to_category_list_page() {
        // Added check for the specific step in your feature file
        Assert.assertTrue("Not on the category list page!", categoryListPage.isCategoryTableDisplayed());
    }

    @Then("Updated category should be displayed in the list")
    public void updated_category_should_be_displayed_in_the_list() {
        boolean isDisplayed = categoryListPage.isUpdatedCategoryDisplayed(updatedCategoryName);
        Assert.assertTrue("Updated category '" + updatedCategoryName + "' was not found in the list!", isDisplayed);
    }
}