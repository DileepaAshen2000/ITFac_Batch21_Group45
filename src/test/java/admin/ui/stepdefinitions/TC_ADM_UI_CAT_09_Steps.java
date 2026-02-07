package admin.ui.stepdefinitions;

import admin.ui.pages.CategoryListPage;
import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class TC_ADM_UI_CAT_09_Steps {

    private WebDriver driver = DriverFactory.getDriver();
    private CategoryListPage categoryListPage = new CategoryListPage(driver);

    @And("all categories are deleted")
    public void all_categories_are_deleted() {
        categoryListPage.navigateToCategoryList();

        // Check if there are rows before trying to delete
        int rowCount = categoryListPage.getCategoryRowCount();
        System.out.println("Initial rows found: " + rowCount);

        while (rowCount > 0) {
            try {
                categoryListPage.clickDeleteForFirstCategory();
                categoryListPage.confirmDeletion();
                // Brief wait for the row to disappear from the DOM
                Thread.sleep(1000);
                rowCount = categoryListPage.getCategoryRowCount();
            } catch (Exception e) {
                System.out.println("Deletions complete or button no longer found.");
                break;
            }
        }
    }

    @When("Admin navigates to {string}")
    public void admin_navigates_to(String path) {
        // Logic to navigate to the specific URL path
        driver.get("http://localhost:8008" + path);
    }

    @Then("the message {string} should be displayed instead of table rows")
    public void the_message_should_be_displayed(String expectedMessage) {
        String actualMessage = categoryListPage.getEmptyListMessage();
        Assert.assertEquals("The empty list message was not correct!", expectedMessage, actualMessage);

        // Also verify that the row count is 1 (the row containing the message) or 0 actual data rows
        Assert.assertTrue("Table should not contain data rows", categoryListPage.getCategoryRowCount() <= 1);
    }
}