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
    By categoryTable = By.cssSelector("table.table");
    By firstEditIcon = By.cssSelector("a[title='Edit']");

    // NEW LOCATOR FOR DELETE
    By firstDeleteButton = By.cssSelector("button[title='Delete']");

    // Edit Page Locators
    By categoryNameField = By.id("name");
    By saveButton = By.cssSelector("button[type='submit']");

    public CategoryListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void navigateToCategoryList() {
        driver.get("http://localhost:8008/ui/categories");
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTable));
    }

    public boolean isCategoryTableDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTable)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // --- Actions for TC_06 (Edit) ---
    public void clickEditForFirstCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(firstEditIcon)).click();
    }

    public void updateCategoryName(String newName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryNameField)).clear();
        driver.findElement(categoryNameField).sendKeys(newName);
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTable));
    }

    public boolean isUpdatedCategoryDisplayed(String categoryName) {
        By row = By.xpath("//td[contains(text(), '" + categoryName + "')]");
        return !driver.findElements(row).isEmpty();
    }

    // --- NEW Actions for TC_07 (Delete) ---
    public void clickDeleteForFirstCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(firstDeleteButton)).click();
    }

    public void confirmDeletion() {
        // Handles the browser confirmation popup (onsubmit="return confirm(...)")
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}