package admin.ui.pages;

import common.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryListPage {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // ================== COMMON LOCATORS ==================

    private By categoryTable = By.tagName("table");

    private By addCategoryBtn =
            By.xpath("//a[contains(text(),'Add A Category')]");

    // ================== EDIT CATEGORY (CAT_06) ==================

    private By firstEditIcon =
            By.xpath("//table/tbody/tr[1]//a[contains(@href,'edit')]");

    private By categoryNameInput =
            By.id("name");

    private By saveButton =
            By.xpath("//button[contains(text(),'Save')]");

    // ================== NAVIGATION ==================

    public void navigateToCategoryList() {
        driver.get("http://localhost:8008/ui/categories");
    }

    // ================== COMMON ACTIONS ==================

    public boolean isCategoryTableDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTable))
                .isDisplayed();
    }

    public void clickAddCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(addCategoryBtn))
                .click();
    }

    // ================== CAT_06 ACTIONS ==================

    public void clickEditForFirstCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(firstEditIcon))
                .click();
    }

    public void updateCategoryName(String newName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryNameInput))
                .clear();
        driver.findElement(categoryNameInput).sendKeys(newName);
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton))
                .click();
    }

    public boolean isUpdatedCategoryDisplayed(String categoryName) {
        By updatedCategory =
                By.xpath("//table//td[contains(text(),'" + categoryName + "')]");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(updatedCategory))
                .isDisplayed();
    }
}
