package admin.ui.stepdefinitions;

import admin.ui.pages.AddCategoryPage;
import admin.ui.pages.CategoryListPage;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class TC_ADM_UI_CAT_02_Steps {

    CategoryListPage categoryListPage = new CategoryListPage();
    AddCategoryPage addCategoryPage = new AddCategoryPage();

    @When("Admin navigates to add category page")
    public void admin_navigates_to_add_category_page() {
        categoryListPage.navigateToCategoryList();
        categoryListPage.clickAddCategory();

        Assert.assertTrue(
                "Add Category page not opened",
                addCategoryPage.isAddCategoryPageDisplayed()
        );
    }

    @When("Admin enters a valid category name {string}")
    public void admin_enters_a_valid_category_name(String name) {
        addCategoryPage.enterCategoryName(name);
    }

    @When("Admin clicks save button")
    public void admin_clicks_save_button() {
        addCategoryPage.clickSave();
    }

    @Then("Category should be created successfully")
    public void category_should_be_created_successfully() {
        Assert.assertTrue(
                "Category list page not displayed after save",
                categoryListPage.isCategoryTableDisplayed()
        );
    }
}
