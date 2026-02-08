package admin.ui.stepdefinitions;

import admin.ui.pages.AdminPlantListPage;
import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class TC_ADM_UI_PLANT_01_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final AdminPlantListPage plantList = new AdminPlantListPage(driver);

    @When("Admin navigates to plant list page for TC01")
    public void admin_navigates_to_plant_list_page_for_tc01() {
        plantList.goToPlantsPage();
    }

    @Then("Plant list page should be visible for Admin")
    public void plant_list_page_should_be_visible_for_admin() {
        Assert.assertTrue("Admin plant table not visible", plantList.isTableDisplayed());
        Assert.assertTrue("URL is not /ui/plants", driver.getCurrentUrl().contains("/ui/plants"));
    }
}
