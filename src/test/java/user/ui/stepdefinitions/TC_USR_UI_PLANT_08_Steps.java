package user.ui.stepdefinitions;

import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import user.ui.pages.UserPlantListPage;

public class TC_USR_UI_PLANT_08_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final UserPlantListPage plantList = new UserPlantListPage(driver);

    @When("User goes to next page")
    public void user_goes_to_next_page() {
        plantList.goNextPageIfPossible();
    }

    @Then("Plant list should still be displayed")
    public void plant_list_should_still_be_displayed() {
        Assert.assertTrue("Plant table not visible", driver.getCurrentUrl().contains("/ui/plants"));
    }
}
