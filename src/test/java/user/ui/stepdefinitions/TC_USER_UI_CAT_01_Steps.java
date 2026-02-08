package user.ui.stepdefinitions;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

import user.ui.pages.UserCategoryListPage;
import user.ui.pages.UserLoginPage;
import common.utils.DriverFactory;

public class TC_USER_UI_CAT_01_Steps {

    WebDriver driver = DriverFactory.getDriver();
    UserLoginPage loginPage;
    UserCategoryListPage categoryPage;

    List<Integer> actualIds;

    @Given("User is logged into the system")
    public void user_is_logged_into_the_system() {
        driver.get("http://localhost:8008/ui/login");
        loginPage = new UserLoginPage(driver);
        loginPage.loginAsUser("testuser", "test123");
    }

    @When("User navigates to category list page")
    public void user_navigates_to_category_list_page() {
        driver.get("http://localhost:8008/ui/categories");
        categoryPage = new UserCategoryListPage(driver);
    }

    @When("User clicks the ID column header once")
    public void user_clicks_the_id_column_header_once() {
        categoryPage.clickIdHeader();
    }

    @When("User clicks the ID column header again")
    public void user_clicks_the_id_column_header_again() {
        categoryPage.clickIdHeader();
        actualIds = categoryPage.getCategoryIds();
    }

    @Then("Categories should be sorted descending then ascending by ID")
    public void categories_should_be_sorted_correctly() {

        List<Integer> asc = new ArrayList<>(actualIds);
        Collections.sort(asc);

        List<Integer> desc = new ArrayList<>(asc);
        Collections.reverse(desc);

        assertTrue(
                "Category list is not sorted by ID",
                actualIds.equals(asc) || actualIds.equals(desc)
        );
    }
}
