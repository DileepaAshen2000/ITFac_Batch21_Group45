package admin.ui.stepdefinitions;

import admin.ui.pages.CategoryListPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class TC_ADM_UI_CAT_02_Steps {

    CategoryListPage categoryPage = new CategoryListPage();
    String categoryToDelete = "TestCategory"; // Change to a valid category in your table

    @And("Category exists and is not in use")
    public void category_exists_and_not_in_use() {
        // Navigate to categories page after login
        categoryPage.navigateToCategoryList();
        // Optional: verify the table is displayed
        assertTrue(categoryPage.isCategoryTableDisplayed());
    }

    @When("Admin clicks delete icon for a category")
    public void admin_clicks_delete_icon() {
        categoryPage.clickDeleteForFirstCategory();
    }

    @And("Admin confirms the deletion message")
    public void admin_confirms_deletion() {
        categoryPage.confirmDeletionAlert();
    }

    @Then("The category should be removed from the list")
    public void category_should_be_removed() {
        assertTrue(categoryPage.isCategoryDeletedFromList(categoryToDelete));
    }

    @And("No error should be displayed")
    public void no_error_should_be_displayed() {
        assertTrue(categoryPage.isNoErrorMessageDisplayed());
    }
}
