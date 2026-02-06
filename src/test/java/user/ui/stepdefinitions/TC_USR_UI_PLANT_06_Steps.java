package user.ui.stepdefinitions;

import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import user.ui.pages.UserPlantListPage;

import java.util.List;

public class TC_USR_UI_PLANT_06_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final UserPlantListPage plantList = new UserPlantListPage(driver);

    private List<String> names;

//    @Given("User is logged into the system")
//    public void user_is_logged_into_the_system() {
//        // reuse existing user login
//        // new LoginPage(driver).loginAsUser();
//    }

//    @When("User navigates to plant list page for")
//    public void user_navigates_to_plant_list_page() {
//        plantList.goToPlantsPage();
//    }

//    @When("User sorts plants by name")
//    public void user_sorts_plants_by_name() {
//        plantList.clickSortByName();
//        names = plantList.getColumnTexts(1);
//    }

//    @Then("Plant list should be sorted alphabetically by name")
//    public void plant_list_should_be_sorted_alphabetically_by_name() {
//        Assert.assertTrue("Plant list not sorted alphabetically", plantList.isAlphabeticallySorted(names));
//    }
}
