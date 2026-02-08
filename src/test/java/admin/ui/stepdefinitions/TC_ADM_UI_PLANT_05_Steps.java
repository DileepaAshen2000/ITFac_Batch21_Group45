package admin.ui.stepdefinitions;

import admin.ui.pages.AdminPlantFormPage;
import admin.ui.pages.AdminPlantListPage;
import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class TC_ADM_UI_PLANT_05_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final AdminPlantListPage plantList = new AdminPlantListPage(driver);
    private final AdminPlantFormPage plantForm = new AdminPlantFormPage(driver);

    private String createdPlantName;

    @When("Admin navigates to plant list page for TC05")
    public void admin_navigates_to_plant_list_page_for_tc05() {
        plantList.goToPlantsPage();
    }

    @When("Admin enters valid plant data and submits")
    public void admin_enters_valid_plant_data_and_submits() {
        plantForm.waitForFormVisible();

        createdPlantName = "AutoPlant_" + System.currentTimeMillis();
        plantForm.setName(createdPlantName);

        // pick first real category option (index 1)
        plantForm.selectCategoryByIndex(1);

        plantForm.setPrice("12.50");
        plantForm.setStock("10");

        plantForm.clickSave();
        plantList.waitForTableVisible();
    }

    @Then("Newly added plant should be visible in plant list")
    public void newly_added_plant_should_be_visible_in_plant_list() {
        // Search for the created plant to avoid pagination issues
        plantList.setSearchKeyword(createdPlantName);
        plantList.clickSearch();
        Assert.assertTrue("Created plant not found in list", plantList.isPlantPresentByName(createdPlantName));
    }
}
