package admin.ui.stepdefinitions;

import admin.ui.pages.AdminPlantFormPage;
import admin.ui.pages.AdminPlantListPage;
import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class TC_ADM_UI_PLANT_07_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final AdminPlantListPage plantList = new AdminPlantListPage(driver);
    private final AdminPlantFormPage plantForm = new AdminPlantFormPage(driver);

    private String originalName;
    private String updatedName;


    @When("Admin navigates to plant list page for TC07")
    public void admin_navigates_to_plant_list_page() {
        plantList.goToPlantsPage();
    }

    @When("Admin edits first plant and updates details")
    public void admin_edits_first_plant_and_updates_details() {
        originalName = plantList.getFirstRowPlantName();
        plantList.clickEditFirstRow();

        plantForm.waitForFormVisible();

        updatedName = originalName + " Updated";
        plantForm.setName(updatedName);
        plantForm.setPrice("9.99");
        plantForm.setStock("10");

        plantForm.clickSave();
        plantList.waitForTableVisible();
    }

    @Then("Updated plant should be shown in plant list")
    public void updated_plant_should_be_shown_in_plant_list() {
        Assert.assertTrue("Updated plant name not found in list", plantList.isPlantPresentByName(updatedName));
    }
}
