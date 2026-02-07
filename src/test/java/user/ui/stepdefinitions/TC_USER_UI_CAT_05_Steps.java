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

public class TC_USER_UI_CAT_05_Steps {

    WebDriver driver = DriverFactory.getDriver();
    UserLoginPage loginPage;
    UserCategoryListPage categoryPage;

    List<String> firstClickNames;
    List<String> secondClickNames;

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

    @When("User clicks the Name column header once")
    public void user_clicks_the_name_column_header_once() {
        categoryPage.clickNameHeader();
        firstClickNames = categoryPage.getVisibleCategoryNames();
    }

    @When("User clicks the Name column header again")
    public void user_clicks_the_name_column_header_again() {
        categoryPage.clickNameHeader();
        secondClickNames = categoryPage.getVisibleCategoryNames();
    }

    @Then("Categories should be sorted alphabetically then reverse-alphabetically by Name")
    public void categories_should_be_sorted_alphabetically_by_name() {
        List<String> sortedAsc = new ArrayList<>(firstClickNames);
        Collections.sort(sortedAsc);

        List<String> sortedDesc = new ArrayList<>(sortedAsc);
        Collections.reverse(sortedDesc);

        boolean isFirstAsc = firstClickNames.equals(sortedAsc);
        boolean isFirstDesc = firstClickNames.equals(sortedDesc);
        boolean isSecondAsc = secondClickNames.equals(sortedAsc);
        boolean isSecondDesc = secondClickNames.equals(sortedDesc);

        assertTrue("The name sorting did not toggle correctly between clicks.",
                (isFirstAsc && isSecondDesc) || (isFirstDesc && isSecondAsc));
    }
}