
package admin.ui.stepdefinitions;

import admin.ui.pages.AdminPlantListPage;
import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class TC_ADM_UI_PLANT_06_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final AdminPlantListPage plantList = new AdminPlantListPage(driver);

    @When("Admin navigates to plant list page for TC06")
    public void admin_navigates_to_plant_list_page() {
        plantList.goToPlantsPage();
        Assert.assertTrue("Plant table not visible", plantList.isTableDisplayed());
    }

    @Then("Edit option should be visible for a plant row")
    public void edit_option_should_be_visible_for_a_plant_row() {
        Assert.assertTrue("Edit button not visible for first row", plantList.isEditVisibleForFirstRow());
    }
}
