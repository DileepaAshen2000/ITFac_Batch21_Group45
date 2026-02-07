package user.ui.stepdefinitions;

import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import user.ui.pages.UserPlantListPage;

import java.util.List;

public class TC_USR_UI_PLANT_10_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final UserPlantListPage plantList = new UserPlantListPage(driver);

    private List<String> names;


    @When("User clicks reset")
    public void user_clicks_reset() {
        plantList.clickReset();
        names = plantList.getColumnTexts(1);
    }

}
