package admin.ui.stepdefinitions;

import admin.ui.pages.AdminPlantFormPage;
import admin.ui.pages.AdminPlantListPage;
import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class TC_ADM_UI_PLANT_03_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final AdminPlantListPage plantList = new AdminPlantListPage(driver);
    private final AdminPlantFormPage plantForm = new AdminPlantFormPage(driver);

    @When("Admin navigates to plant list page for TC03")
    public void admin_navigates_to_plant_list_page_for_tc03() {
        plantList.goToPlantsPage();
    }

    @When("Admin clicks Add Plant button")
    public void admin_clicks_add_plant_button() {
        plantList.clickAddPlant();
    }

    @Then("Add Plant form should be displayed")
    public void add_plant_form_should_be_displayed() {
        plantForm.waitForFormVisible();
        Assert.assertTrue("Plant form heading not visible", plantForm.isFormVisible());
    }
}
