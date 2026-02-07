package user.ui.stepdefinitions;

import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import user.ui.pages.UserPlantListPage;


public class TC_USR_UI_PLANT_03_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final UserPlantListPage plantList = new UserPlantListPage(driver);

    @When("User navigates to plant list page for TC03")
    public void user_navigates_to_plant_list_page_for_tc03() {
        plantList.goToPlantsPage();
    }

    @Then("Edit and Delete actions should NOT be visible for User.")
    public void edit_and_delete_actions_should_not_be_visible_for_user() {
        Assert.assertFalse("Edit/Delete found for User (should be hidden)", plantList.areEditDeleteVisible());
    }
}
