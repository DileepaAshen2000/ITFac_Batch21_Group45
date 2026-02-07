package admin.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryListPage {

    WebDriver driver;
    WebDriverWait wait;

    // UPDATED LOCATORS based on your provided HTML
    // Using class names because the id="categoryTable" is missing in your HTML
    By categoryTable = By.cssSelector("table.table");

    // Using the 'title' attribute of the anchor tag for the edit button
    By firstEditIcon = By.cssSelector("a[title='Edit']");

    // Common IDs for category forms - please verify these in your Edit Page HTML
    By categoryNameField = By.id("name");
    By saveButton = By.cssSelector("button[type='submit']");

    public CategoryListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void navigateToCategoryList() {
        driver.get("http://localhost:8008/ui/categories");
        // Wait for the table to appear using its CSS class
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTable));
    }

    public boolean isCategoryTableDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTable)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickEditForFirstCategory() {
        // Wait for the specific edit link to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(firstEditIcon)).click();
    }

    public void updateCategoryName(String newName) {
        // Wait for the input field on the edit page
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryNameField)).clear();
        driver.findElement(categoryNameField).sendKeys(newName);
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        // Wait for the browser to return to the list page
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTable));
    }

    public boolean isUpdatedCategoryDisplayed(String categoryName) {
        // Check if a table cell contains the new category name
        By updatedRow = By.xpath("//td[contains(text(), '" + categoryName + "')]");
        return !driver.findElements(updatedRow).isEmpty();
    }
}