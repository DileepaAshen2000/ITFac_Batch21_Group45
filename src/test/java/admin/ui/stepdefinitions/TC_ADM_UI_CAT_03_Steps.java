package admin.ui.stepdefinitions;

import admin.ui.pages.AddCategoryPage;
import admin.ui.pages.CategoryListPage;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class TC_ADM_UI_CAT_03_Steps {

    CategoryListPage categoryListPage = new CategoryListPage();
    AddCategoryPage addCategoryPage = new AddCategoryPage();

    @When("Admin navigates to add category page for sub category")
    public void admin_navigates_to_add_category_page_for_sub_category() {
        categoryListPage.navigateToCategoryList();
        categoryListPage.clickAddCategory();

        Assert.assertTrue(
                "Add Category page not opened",
                addCategoryPage.isAddCategoryPageDisplayed()
        );
    }

    @When("Admin enters sub category name {string}")
    public void admin_enters_sub_category_name(String name) {
        addCategoryPage.enterCategoryName(name);
    }

    @When("Admin selects parent category {string}")
    public void admin_selects_parent_category(String parent) {
        addCategoryPage.selectParentCategory(parent);
    }

    @When("Admin saves the sub category")
    public void admin_saves_the_sub_category() {
        addCategoryPage.clickSave();
    }

    @Then("Sub category should be created successfully")
    public void sub_category_should_be_created_successfully() {
        Assert.assertTrue(
                "Category list page not displayed after save",
                categoryListPage.isCategoryTableDisplayed()
        );
    }
}
