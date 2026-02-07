package admin.ui.pages;

import common.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryListPage {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    // ================== COMMON LOCATORS ==================
    private By categoryTable = By.tagName("table");
    private By addCategoryBtn = By.xpath("//a[contains(text(),'Add A Category')]");
    private By errorMsg = By.cssSelector(".error-message");

    // ================== LOGIN LOCATORS ==================
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("loginBtn");

    // ================== EDIT CATEGORY ==================
    private By firstEditIcon = By.xpath("//table/tbody/tr[1]//a[contains(@href,'edit')]");
    private By categoryNameInput = By.id("name");
    private By saveButton = By.xpath("//button[contains(text(),'Save')]");

    // ================== DELETE CATEGORY ==================
    private By firstDeleteIcon = By.xpath("//table/tbody/tr[1]//a[contains(@href,'delete')]");


    // ================== NAVIGATION ==================
    public void navigateToCategoryList() {
        driver.get("http://localhost:8008/ui/categories");
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTable));
    }

    // ================== COMMON ==================
    public boolean isCategoryTableDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTable)).isDisplayed();
    }

    // ================== EDIT ==================
    public void clickEditForFirstCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(firstEditIcon)).click();
    }

    public void updateCategoryName(String newName) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(categoryNameInput));
        input.clear();
        input.sendKeys(newName);
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public boolean isUpdatedCategoryDisplayed(String categoryName) {
        By updatedCategory = By.xpath("//table//td[contains(text(),'" + categoryName + "')]");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(updatedCategory)).isDisplayed();
    }

    // ================== DELETE ==================
    public void clickDeleteForFirstCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(firstDeleteIcon)).click();
    }

    public void confirmDeletionAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public boolean isCategoryDeletedFromList(String categoryName) {
        By deletedCategory = By.xpath("//table//td[contains(text(),'" + categoryName + "')]");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(deletedCategory));
    }

    public boolean isNoErrorMessageDisplayed() {
        return driver.findElements(errorMsg).isEmpty();
    }
}
