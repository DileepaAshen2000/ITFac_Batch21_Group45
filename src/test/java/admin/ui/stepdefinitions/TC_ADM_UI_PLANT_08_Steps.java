package admin.ui.stepdefinitions;

import admin.ui.pages.AdminPlantListPage;
import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class TC_ADM_UI_PLANT_08_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final AdminPlantListPage plantList = new AdminPlantListPage(driver);

//    @Given("Admin is logged into the system")
//    public void admin_is_logged_into_the_system() {
//        // reuse existing login
//    }

    @When("Admin navigates to plant list page for TC08")
    public void admin_navigates_to_plant_list_page() {
        plantList.goToPlantsPage();
    }

    @Then("Delete option should be visible for a plant row")
    public void delete_option_should_be_visible_for_a_plant_row() {
        Assert.assertTrue("Delete button not visible for first row", plantList.isDeleteVisibleForFirstRow());
    }
}
