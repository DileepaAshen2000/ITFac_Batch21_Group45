package user.ui.stepdefinitions;

import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import user.ui.pages.UserPlantListPage;

public class TC_USR_UI_PLANT_02_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final UserPlantListPage plantList = new UserPlantListPage(driver);

    @When("User navigates to plant list page for TC02")
    public void user_navigates_to_plant_list_page_for_tc02() {
        plantList.goToPlantsPage();
    }

    @Then("Add Plant button should NOT be visible for User")
    public void add_plant_button_should_not_be_visible_for_user() {
        Assert.assertFalse("Add Plant is visible for User (should be hidden)", plantList.isAddPlantVisible());
    }
}
