package admin.ui.stepdefinitions;

import admin.ui.pages.AdminPlantListPage;
import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class TC_ADM_UI_PLANT_02_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final AdminPlantListPage plantList = new AdminPlantListPage(driver);

    @When("Admin navigates to plant list page for TC02")
    public void admin_navigates_to_plant_list_page_for_tc02() {
        plantList.goToPlantsPage();
    }

    @Then("Add Plant button should be visible for Admin")
    public void add_plant_button_should_be_visible_for_admin() {
        Assert.assertTrue("Add Plant button not visible for Admin", plantList.isAddPlantVisible());
    }
}
