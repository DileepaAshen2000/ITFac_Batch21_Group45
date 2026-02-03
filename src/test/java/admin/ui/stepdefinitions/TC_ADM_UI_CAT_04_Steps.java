package admin.ui.stepdefinitions;

import admin.ui.pages.AddCategoryPage;
import admin.ui.pages.CategoryListPage;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class TC_ADM_UI_CAT_04_Steps {

    CategoryListPage categoryListPage = new CategoryListPage();
    AddCategoryPage addCategoryPage = new AddCategoryPage();

    @When("Admin navigates to add category page for validation")
    public void admin_navigates_to_add_category_page_for_validation() {
        categoryListPage.navigateToCategoryList();
        categoryListPage.clickAddCategory();

        Assert.assertTrue(
                "Add Category page not opened",
                addCategoryPage.isAddCategoryPageDisplayed()
        );
    }

    @When("Admin enters short category name {string}")
    public void admin_enters_short_category_name(String name) {
        addCategoryPage.enterCategoryName(name);
    }

    @When("Admin submits the add category form")
    public void admin_submits_the_add_category_form() {
        addCategoryPage.clickSave();
    }

    @Then("Validation error message should be displayed")
    public void validation_error_message_should_be_displayed() {
        Assert.assertTrue(
                "Validation error not displayed",
                addCategoryPage.isValidationErrorDisplayed()
        );
    }

    @Then("Admin should remain on add category page")
    public void admin_should_remain_on_add_category_page() {
        Assert.assertTrue(
                "Form was submitted unexpectedly",
                addCategoryPage.isAddCategoryPageDisplayed()
        );
    }
}
