package admin.ui.stepdefinitions;

import admin.ui.pages.CategoryListPage;
import admin.ui.pages.LoginPage;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class TC_ADM_UI_CAT_06_Steps {

    LoginPage loginPage = new LoginPage();
    CategoryListPage categoryListPage = new CategoryListPage();

    @Given("Admin is logged into the system")
    public void admin_is_logged_in() {
        loginPage.openLoginPage();
        loginPage.loginAsAdmin("admin", "admin123");
    }

    @Given("Admin navigates to category list page")
    public void admin_navigates_to_category_list_page() {
        categoryListPage.navigateToCategoryList();
        Assert.assertTrue(categoryListPage.isCategoryTableDisplayed());
    }

    @When("Admin clicks edit icon for an existing category")
    public void admin_clicks_edit_icon_for_existing_category() {
        categoryListPage.clickEditForFirstCategory();
    }

    @When("Admin updates category name")
    public void admin_updates_category_name() {
        categoryListPage.updateCategoryName("Updated Category CAT_06");
    }

    @When("Admin clicks save button")
    public void admin_clicks_save_button() {
        categoryListPage.clickSaveButton();
    }

    @Then("Admin should be redirected to category list page")
    public void admin_should_be_redirected_to_category_list_page() {
        Assert.assertTrue(categoryListPage.isCategoryTableDisplayed());
    }

    @Then("Updated category should be displayed in the list")
    public void updated_category_should_be_displayed_in_the_list() {
        Assert.assertTrue(
                categoryListPage.isUpdatedCategoryDisplayed("Updated Category CAT_06")
        );
    }
}
