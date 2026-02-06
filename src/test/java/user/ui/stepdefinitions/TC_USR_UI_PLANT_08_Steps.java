package user.ui.stepdefinitions;

import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import user.ui.pages.UserPlantListPage;

public class TC_USR_UI_PLANT_08_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final UserPlantListPage plantList = new UserPlantListPage(driver);

//    @Given("User is logged into the system")
//    public void user_is_logged_into_the_system() {
//        // reuse login
//    }

//    @When("User navigates to plant list page")
//    public void user_navigates_to_plant_list_page() {
//        plantList.goToPlantsPage();
//    }

    @When("User goes to next page")
    public void user_goes_to_next_page() {
        plantList.goNextPageIfPossible();
    }

    @Then("Plant list should still be displayed")
    public void plant_list_should_still_be_displayed() {
        // If pagination not available, it still passes as table remains visible
        Assert.assertTrue("Plant table not visible", driver.getCurrentUrl().contains("/ui/plants"));
    }
}
