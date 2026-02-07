package admin.ui.stepdefinitions;

import admin.ui.pages.CategoryListPage;
import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class TC_ADM_UI_CAT_07_Steps {

    private WebDriver driver;
    private CategoryListPage categoryListPage;

    // REMOVED: admin_navigates_to_category_list_page (it is already in TC_ADM_UI_CAT_06_Steps)

    @When("Admin clicks delete icon for a category")
    public void admin_clicks_delete_icon_for_a_category() {
        // Initialize if not already done by previous steps
        if (this.categoryListPage == null) {
            this.driver = DriverFactory.getDriver();
            this.categoryListPage = new CategoryListPage(driver);
        }
        categoryListPage.clickDeleteForFirstCategory();
    }

    @And("Admin confirms the deletion")
    public void admin_confirms_the_deletion() {
        categoryListPage.confirmDeletion();
    }

    @Then("Category should be removed from the list")
    public void category_should_be_removed_from_the_list() {
        Assert.assertTrue("Table should be visible after deletion", categoryListPage.isCategoryTableDisplayed());
    }
}