package admin.ui.stepdefinitions;

import admin.ui.pages.AdminPlantListPage;
import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class TC_ADM_UI_PLANT_09_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private final AdminPlantListPage plantList = new AdminPlantListPage(driver);

    private String deletedName;

    @When("Admin navigates to plant list page for TC09")
    public void admin_navigates_to_plant_list_page() {
        plantList.goToPlantsPage();
    }

    @When("Admin deletes the first plant and confirms")
    public void admin_deletes_the_first_plant_and_confirms() {
        deletedName = plantList.getFirstRowPlantName();
        plantList.clickDeleteFirstRow();
        plantList.waitForTableVisible();
    }

    @Then("Deleted plant should not be present in plant list")
    public void deleted_plant_should_not_be_present_in_plant_list() {
        Assert.assertFalse("Deleted plant still appears in list", plantList.isPlantPresentByName(deletedName));
    }
}
