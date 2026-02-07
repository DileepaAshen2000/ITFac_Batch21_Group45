package user.ui.stepdefinitions;

import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import user.ui.pages.UserPlantListPage;

import java.util.List;

public class TC_USR_UI_PLANT_05_Steps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final UserPlantListPage plantList = new UserPlantListPage(driver);



    private String selectedCategory;
    private List<String> categoryColumnValues;

    @When("User navigates to plant list page for TC05")
    public void user_navigates_to_plant_list_page_for_tc05() {
        plantList.goToPlantsPage();
    }

    @When("User applies a category filter")
    public void user_applies_a_category_filter() {
        List<String> options = plantList.getAllCategoryFilterOptions();

        Assert.assertTrue("No category options available to filter", options.size() > 1);

        selectedCategory = options.get(1);
        plantList.selectCategoryFilterByVisibleText(selectedCategory);
        plantList.clickSearch();

        categoryColumnValues = plantList.getColumnTexts(2);
    }

    @Then("Plant list should be filtered by selected category")
    public void plant_list_should_be_filtered_by_selected_category() {
        Assert.assertFalse("No rows after applying category filter", categoryColumnValues.isEmpty());

        boolean allMatch = categoryColumnValues.stream()
                .allMatch(c -> c.equalsIgnoreCase(selectedCategory));

        Assert.assertTrue(
                "Category filtering failed. Expected all rows = " + selectedCategory + " but got: " + categoryColumnValues,
                allMatch
        );
    }
}
