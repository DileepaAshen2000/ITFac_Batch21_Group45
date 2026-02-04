package admin.ui.stepdefinitions;

import admin.ui.pages.AddCategoryPage;
import admin.ui.pages.CategoryListPage;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class TC_ADM_UI_CAT_05_Steps {

    CategoryListPage categoryListPage = new CategoryListPage();
    AddCategoryPage addCategoryPage = new AddCategoryPage();

    @When("Admin navigates to add category page for max length validation")
    public void admin_navigates_to_add_category_page_for_max_length_validation() {
        categoryListPage.navigateToCategoryList();
        categoryListPage.clickAddCategory();
        Assert.assertTrue(addCategoryPage.isAddCategoryPageDisplayed());
    }

    @When("Admin enters long category name {string}")
    public void admin_enters_long_category_name(String name) {
        addCategoryPage.enterCategoryName(name);
    }

    @When("Admin attempts to save category")
    public void admin_attempts_to_save_category() {
        addCategoryPage.clickSave();
    }

    @Then("Length validation error should be displayed")
    public void length_validation_error_should_be_displayed() {
        Assert.assertTrue(addCategoryPage.isValidationErrorDisplayed());
    }

    @Then("Category should not be created")
    public void category_should_not_be_created() {
        Assert.assertTrue(addCategoryPage.isAddCategoryPageDisplayed());
    }
}
