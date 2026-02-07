package user.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserCategoryListPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // --- LOCATORS ---
    private By pageHeader = By.xpath("//*[contains(text(),'Category List')]");
    private By categoriesMenuLink = By.xpath("//a[contains(@href, '/ui/categories')]");
    private By idHeader = By.xpath("//th[contains(text(),'ID')] | //a[contains(@href, 'sortField=id')]");
    private By nameHeader = By.xpath("//th[contains(text(),'Name')] | //a[contains(@href, 'sortField=name')]");
    private By idColumnCells = By.xpath("//table/tbody/tr/td[1]");
    private By categoryNameCells = By.xpath("//table/tbody/tr/td[2]");
    private By searchBox = By.name("name");
    private By searchButton = By.xpath("//button[contains(text(),'Search')]");
    private By editButton = By.xpath("//a[contains(@href, 'edit')]");

    public UserCategoryListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- NAVIGATION & LOAD CHECKS ---
    public boolean isPageLoaded() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickCategoriesMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(categoriesMenuLink)).click();
    }

    // --- SORTING METHODS ---
    public void clickIdHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(idHeader)).click();
    }

    public void clickNameHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(nameHeader)).click();
    }

    // --- DATA RETRIEVAL METHODS ---
    public List<Integer> getCategoryIds() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(idColumnCells));
        return driver.findElements(idColumnCells).stream()
                .map(el -> Integer.parseInt(el.getText().trim()))
                .collect(Collectors.toList());
    }

    public List<String> getVisibleCategoryNames() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(categoryNameCells));
        return driver.findElements(categoryNameCells).stream()
                .map(el -> el.getText().trim())
                .collect(Collectors.toList());
    }

    // --- SEARCH & PERMISSIONS ---
    public void searchCategory(String categoryName) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        input.clear();
        input.sendKeys(categoryName);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public boolean isEditOptionVisible() {
        return !driver.findElements(editButton).isEmpty();
    }
}