package admin.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryListPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By categoryTable = By.id("categoryTable");  // update with actual locator
    By firstEditIcon = By.cssSelector(".edit-category");  // update as needed
    By categoryNameField = By.id("categoryName");  // update
    By saveButton = By.id("saveCategory");  // update

    public CategoryListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Navigate explicitly to /categories page
    public void navigateToCategoryList() {
        driver.get("http://localhost:8008/ui/categories");

        // Wait for table to be displayed
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTable));
    }

    public boolean isCategoryTableDisplayed() {
        return driver.findElement(categoryTable).isDisplayed();
    }

    public void clickEditForFirstCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(firstEditIcon)).click();
    }

    public void updateCategoryName(String newName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryNameField)).clear();
        driver.findElement(categoryNameField).sendKeys(newName);
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();

        // Wait for table to reload
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTable));
    }

    public boolean isUpdatedCategoryDisplayed(String categoryName) {
        return !driver.findElements(By.xpath("//td[text()='" + categoryName + "']")).isEmpty();
    }
}
