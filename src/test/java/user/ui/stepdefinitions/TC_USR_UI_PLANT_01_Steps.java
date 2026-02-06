package user.ui.stepdefinitions;

import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import user.ui.pages.UserPlantListPage;

public class TC_USR_UI_PLANT_01_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final UserPlantListPage plantList = new UserPlantListPage(driver);

    @When("User navigates to plant list page for TC01")
    public void user_navigates_to_plant_list_page_for_tc01() {
        plantList.goToPlantsPage();
    }

    @Then("Plant list page should be visible for User")
    public void plant_list_page_should_be_visible_for_user() {
        Assert.assertTrue("User plant table not visible", plantList.isTableDisplayed());
        Assert.assertTrue("URL is not /ui/plants", driver.getCurrentUrl().contains("/ui/plants"));
    }
}
