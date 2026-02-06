package user.ui.stepdefinitions;

import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import user.ui.pages.UserPlantListPage;

import java.util.List;

public class TC_USR_UI_PLANT_04_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final UserPlantListPage plantList = new UserPlantListPage(driver);

    private List<String> names;

    @When("User navigates to plant list page for TC04")
    public void user_navigates_to_plant_list_page_for_tc04() {
        plantList.goToPlantsPage();
    }

    @When("User searches plant by keyword {string}")
    public void user_searches_plant_by_keyword(String keyword) {
        plantList.setSearchKeyword(keyword);
        plantList.clickSearch();
        names = plantList.getColumnTexts(1);
    }

    @Then("Matching plant results should be shown for keyword {string}")
    public void matching_plant_results_should_be_shown_for_keyword(String keyword) {
        Assert.assertFalse("No rows found after search", names == null || names.isEmpty());

        boolean anyMatch = names.stream().anyMatch(n -> n.toLowerCase().contains(keyword.toLowerCase()));
        Assert.assertTrue("No matching plant name contains keyword: " + keyword, anyMatch);
    }
}
