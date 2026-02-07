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

    List<Integer> firstClickIds;
    List<Integer> secondClickIds;

    @Given("User is logged into the system")
    public void user_is_logged_into_the_system() {
        loginPage = new UserLoginPage(driver);
        loginPage.openLoginPage();
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
        firstClickIds = categoryPage.getCategoryIds();
    }

    @When("User clicks the ID column header again")
    public void user_clicks_the_id_column_header_again() {
        categoryPage.clickIdHeader();
        secondClickIds = categoryPage.getCategoryIds();
    }

    @Then("Categories should be sorted descending then ascending by ID")
    public void categories_should_be_sorted_descending_then_ascending_by_id() {
        List<Integer> sortedAsc = new ArrayList<>(firstClickIds);
        Collections.sort(sortedAsc);

        List<Integer> sortedDesc = new ArrayList<>(sortedAsc);
        Collections.reverse(sortedDesc);

        boolean isFirstAsc = firstClickIds.equals(sortedAsc);
        boolean isFirstDesc = firstClickIds.equals(sortedDesc);
        boolean isSecondAsc = secondClickIds.equals(sortedAsc);
        boolean isSecondDesc = secondClickIds.equals(sortedDesc);

        assertTrue("The sorting did not toggle correctly between clicks.",
                (isFirstAsc && isSecondDesc) || (isFirstDesc && isSecondAsc));
    }
}