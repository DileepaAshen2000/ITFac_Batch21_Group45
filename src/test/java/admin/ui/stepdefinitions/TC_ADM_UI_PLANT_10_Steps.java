package admin.ui.stepdefinitions;

import admin.ui.pages.AdminPlantFormPage;
import admin.ui.pages.AdminPlantListPage;
import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class TC_ADM_UI_PLANT_10_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final AdminPlantListPage plantList = new AdminPlantListPage(driver);
    private final AdminPlantFormPage plantForm = new AdminPlantFormPage(driver);


    @When("Admin navigates to plant list page for TC10")
    public void admin_navigates_to_plant_list_page() {
        plantList.goToPlantsPage();
    }

    @When("Admin opens add plant page")
    public void admin_opens_add_plant_page() {
        plantList.clickAddPlant();
        plantForm.waitForFormVisible();
    }

    @When("Admin clicks cancel on plant form")
    public void admin_clicks_cancel_on_plant_form() {
        plantForm.clickCancel();
    }

    @Then("Admin should be redirected to plant list page")
    public void admin_should_be_redirected_to_plant_list_page() {
        Assert.assertTrue("Not redirected to plant list (/ui/plants)", driver.getCurrentUrl().contains("/ui/plants"));
        Assert.assertTrue("Plant table not visible after cancel", plantList.isTableDisplayed());
    }
}
