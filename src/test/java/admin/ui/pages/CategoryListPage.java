package admin.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryListPage {

    WebDriver driver;
    WebDriverWait wait;

    // --- Locators ---
    By categoryTable = By.cssSelector("table.table");

    // Search & Filter Locators
    By searchInput = By.name("name");
    By parentDropdown = By.name("parentId");
    By filterSearchButton = By.xpath("//button[text()='Search']");
    By resetButton = By.xpath("//a[contains(text(), 'Reset')]");

    // Success & Message Locators
    By successAlert = By.cssSelector(".alert-success");
    By emptyListMessage = By.xpath("//*[contains(text(), 'No category found')]");

    // Edit & Delete Locators
    By firstEditIcon = By.cssSelector("a[title='Edit']");
    By firstDeleteButton = By.cssSelector("button[title='Delete']");
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

    // --- Helper Methods for Assertions (Fixes the current Compilation Errors) ---
    public boolean isCategoryTableDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTable)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUpdatedCategoryDisplayed(String categoryName) {
        By row = By.xpath("//td[contains(text(), '" + categoryName + "')]");
        return !driver.findElements(row).isEmpty();
    }

    // --- Actions for TC_08 (Search & Filter) ---
    public void enterSearchTerm(String term) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        searchField.clear();
        searchField.sendKeys(term);
    }

    public void selectParentCategory(String parentName) {
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(parentDropdown));
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(parentName);
    }

    public void clickFilterSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(filterSearchButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTable));
    }

    // --- Actions for TC_09 & TC_10 (Reset & Messages) ---
    public void clickResetButton() {
        wait.until(ExpectedConditions.elementToBeClickable(resetButton)).click();
    }

    public String getEmptyListMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(emptyListMessage)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getSuccessMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successAlert)).getText();
    }

    public boolean isSuccessMessageVisible() {
        try {
            return driver.findElement(successAlert).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // --- Actions for TC_06 (Edit) & TC_07 (Delete) ---
    public void clickEditForFirstCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(firstEditIcon)).click();
    }

    public void updateCategoryName(String newName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryNameField)).clear();
        driver.findElement(categoryNameField).sendKeys(newName);
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public int getCategoryRowCount() {
        return driver.findElements(By.cssSelector("table.table tbody tr")).size();
    }

    public void clickDeleteForFirstCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(firstDeleteButton)).click();
    }

    public void confirmDeletion() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}