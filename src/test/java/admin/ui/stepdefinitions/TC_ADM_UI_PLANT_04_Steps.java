package admin.ui.stepdefinitions;

import admin.ui.pages.AdminPlantFormPage;
import admin.ui.pages.AdminPlantListPage;
import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class TC_ADM_UI_PLANT_04_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final AdminPlantListPage plantList = new AdminPlantListPage(driver);
    private final AdminPlantFormPage plantForm = new AdminPlantFormPage(driver);

    @When("Admin navigates to plant list page for TC04")
    public void admin_navigates_to_plant_list_page_for_tc04() {
        plantList.goToPlantsPage();
    }

    @When("Admin submits plant form without entering data")
    public void admin_submits_plant_form_without_entering_data() {
        plantForm.waitForFormVisible();
        plantForm.clickSave();
    }

    @Then("Validation errors should be shown on plant form")
    public void validation_errors_should_be_shown_on_plant_form() {
        Assert.assertTrue("No validation errors detected", plantForm.hasAnyValidationErrors());
    }
}
