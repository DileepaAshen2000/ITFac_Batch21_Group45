package user.ui.stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import user.ui.pages.UserCategoryListPage;
import common.utils.DriverFactory;
import static org.junit.Assert.assertTrue;

public class TC_USER_UI_CAT_02_Steps {

    WebDriver driver = DriverFactory.getDriver();
    UserCategoryListPage categoryPage = new UserCategoryListPage(driver);

    @When("User clicks on the {string} menu link")
    public void user_clicks_on_the_menu_link(String linkName) {
        // This targets the "Categories" link in your sidebar/menu
        categoryPage.clickCategoriesMenu();
    }

    @Then("the Category List page should load successfully")
    public void the_category_list_page_should_load_successfully() {
        assertTrue("Category List page header not found!", categoryPage.isPageLoaded());
    }

    @Then("all categories should be visible in the table")
    public void all_categories_should_be_visible_in_the_table() {
        // Verifies the table is not empty
        int count = categoryPage.getCategoryIds().size();
        assertTrue("No categories were found in the table!", count > 0);
    }
}