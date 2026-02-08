package admin.ui.stepdefinitions;

import admin.ui.pages.CategoryListPage;
import admin.ui.pages.LoginPage;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class TC_ADM_UI_CAT_01_Steps {

    LoginPage loginPage = new LoginPage();
    CategoryListPage categoryListPage = new CategoryListPage();

    @Given("Admin is logged into the system")
    public void admin_is_logged_in() {
        loginPage.openLoginPage();
        loginPage.loginAsAdmin("admin", "admin123");
        Assert.assertTrue("Login failed", loginPage.isLoginSuccessful());
    }

    @When("Admin navigates to category list page")
    public void admin_navigates_to_category_list_page() {
        categoryListPage.navigateToCategoryList();
    }

    @Then("Category list table should be displayed")
    public void category_list_table_should_be_displayed() {
        Assert.assertTrue(categoryListPage.isCategoryTableDisplayed());
    }
}
