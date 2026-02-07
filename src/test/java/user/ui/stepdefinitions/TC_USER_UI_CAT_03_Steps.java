package user.ui.stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import user.ui.pages.UserCategoryListPage;
import common.utils.DriverFactory;
import static org.junit.Assert.assertTrue;
import java.util.List;

public class TC_USER_UI_CAT_03_Steps {

    WebDriver driver = DriverFactory.getDriver();
    UserCategoryListPage categoryPage = new UserCategoryListPage(driver);

    @When("User enters {string} in the search box")
    public void user_enters_category_name_in_search_box(String name) {
        categoryPage.searchCategory(name);
    }

    @When("User clicks the Search button")
    public void user_clicks_search_button() {
        // Search is handled in the searchCategory method above,
        // but you can separate it if your UI requires two distinct steps.
    }

    @Then("only categories matching {string} should be displayed")
    public void only_matching_categories_should_be_displayed(String expectedName) {
        List<String> actualNames = categoryPage.getVisibleCategoryNames();

        for (String name : actualNames) {
            assertTrue("Found a category that doesn't match search: " + name,
                    name.toLowerCase().contains(expectedName.toLowerCase()));
        }
    }
}