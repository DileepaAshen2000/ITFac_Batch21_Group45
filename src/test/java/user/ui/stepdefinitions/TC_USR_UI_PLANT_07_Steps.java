package user.ui.stepdefinitions;

import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import user.ui.pages.UserPlantListPage;

public class TC_USR_UI_PLANT_07_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final UserPlantListPage plantList = new UserPlantListPage(driver);

    @Then("Low badge should be visible for low stock plants")
    public void low_badge_should_be_visible_for_low_stock_plants() {
        Assert.assertTrue("Low badge not visible (need at least one plant with stock < 5)", plantList.isLowBadgeVisible());
    }
}
