package user.ui.stepdefinitions;

import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import user.ui.pages.UserPlantListPage;

public class TC_USR_UI_PLANT_09_Steps {

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

    @When("User searches plant with keyword {string}")
    public void user_searches_plant_with_keyword(String keyword) {
        plantList.search(keyword);
    }

    @Then("Empty state should be shown")
    public void empty_state_should_be_shown() {
        Assert.assertTrue("Empty state not shown (expected 0 rows or no-data msg)", plantList.isNoResultsShown());
    }
}
