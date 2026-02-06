package user.ui.stepdefinitions.common;

import common.utils.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import user.ui.pages.UserPlantListPage;

import java.util.List;

public class UserCommonSteps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final UserPlantListPage plantList = new UserPlantListPage(driver);
    private List<String> names;

    @When("User navigates to plant list page")
    public void user_navigates_to_plant_list_page() {
        plantList.goToPlantsPage();
    }

    @When("User sorts plants by name")
    public void user_sorts_plants_by_name() {
        plantList.clickSortByName();
        names = plantList.getColumnTexts(1); // column 1 = Name (your method)
    }

//    @Then("Plant list should be sorted alphabetically by name")
//    public void plant_list_should_be_sorted_alphabetically_by_name() {
//        Assert.assertTrue("Plant list not sorted alphabetically", plantList.isAlphabeticallySorted(names));
//    }
    @Then("Plant list should be sorted alphabetically by name")
    public void plant_list_should_be_sorted_alphabetically_by_name() {
        List<String> names = plantList.getColumnTexts(1); // 1 = Name column (adjust if needed)
        Assert.assertTrue("Plant list not sorted alphabetically", plantList.isAlphabeticallySorted(names));
    }

}
