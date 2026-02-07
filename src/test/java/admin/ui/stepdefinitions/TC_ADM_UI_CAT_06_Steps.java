package admin.ui.stepdefinitions;

import admin.ui.pages.CategoryListPage;
import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class TC_ADM_UI_CAT_06_Steps {

    WebDriver driver = DriverFactory.getDriver();
    CategoryListPage categoryListPage = new CategoryListPage(driver);
    String updatedCategoryName = "CAT6";

    @Given("Admin navigates to category list page")
    public void admin_navigates_to_category_list_page() {
        categoryListPage.navigateToCategoryList();
        Assert.assertTrue(categoryListPage.isCategoryTableDisplayed());
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

    @Then("Updated category should be displayed in the list")
    public void updated_category_should_be_displayed_in_the_list() {
        Assert.assertTrue(categoryListPage.isUpdatedCategoryDisplayed(updatedCategoryName));
    }
}
