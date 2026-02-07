package admin.ui.stepdefinitions;

import admin.ui.pages.CategoryListPage;
import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class TC_ADM_UI_CAT_10_Steps {

    private WebDriver driver = DriverFactory.getDriver();
    private CategoryListPage categoryListPage = new CategoryListPage(driver);

    @When("Admin adds a new category with name {string}")
    public void admin_adds_a_new_category(String name) {
        // Ensure we are on the Add page and the field is ready
        categoryListPage.updateCategoryName(name);
        categoryListPage.clickSaveButton();
    }

    @Then("the success message {string} should be displayed")
    public void success_message_displayed(String expected) {
        Assert.assertTrue("Success message not found",
                categoryListPage.getSuccessMessageText().contains(expected));
    }

    @And("the success message should remain visible after 5 seconds")
    public void message_should_remain_visible() throws InterruptedException {
        // Explicitly wait to verify it does NOT auto-disappear
        Thread.sleep(5000);
        Assert.assertTrue("Success message disappeared automatically!",
                categoryListPage.isSuccessMessageVisible());
    }


    @Then("the success message should disappear")
    public void message_should_disappear() {
        Assert.assertFalse("Success message is still visible after Reset!",
                categoryListPage.isSuccessMessageVisible());
    }
}