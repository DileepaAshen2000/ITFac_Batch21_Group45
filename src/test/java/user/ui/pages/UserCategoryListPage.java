package user.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UserCategoryListPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Updated XPath to target the link containing 'sortField=id' regardless of whitespace
    private By idHeader = By.xpath("//a[contains(@href, 'sortField=id')]");
    private By idColumnCells = By.xpath("//table/tbody/tr/td[1]");

    private By categoriesMenuLink = By.xpath("//a[contains(text(),'Categories')]");
    private By pageHeader = By.xpath("//h2[contains(text(),'Category List')]");

    private By searchInput = By.name("name"); // or By.id("name") depending on your HTML
    private By searchButton = By.xpath("//button[contains(text(),'Search')]");
    private By categoryNameCells = By.xpath("//table/tbody/tr/td[2]"); // Assuming Name is the 2nd column
    private By nameHeader = By.xpath("//a[contains(@href, 'sortField=name')]");

    private By editButtons = By.xpath("//a[contains(@href, 'edit')]");

    public UserCategoryListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void clickNameHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(nameHeader)).click();
    }

    public void clickIdHeader() {
        // Wait specifically for the link to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(idHeader)).click();
    }
    public void clickCategoriesMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(categoriesMenuLink)).click();
    }

    public boolean isPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader)).isDisplayed();
    }

    public List<Integer> getCategoryIds() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(idColumnCells));
        List<Integer> ids = new ArrayList<>();
        List<WebElement> cells = driver.findElements(idColumnCells);
        for (WebElement cell : cells) {
            String text = cell.getText().trim();
            if(!text.isEmpty()){
                ids.add(Integer.parseInt(text));
            }
        }
        return ids;
    }

    public void searchCategory(String categoryName) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(categoryName);
        driver.findElement(searchButton).click();
    }

    public List<String> getVisibleCategoryNames() {
        wait.until(ExpectedConditions.presenceOfElementLocated(categoryNameCells));
        List<WebElement> cells = driver.findElements(categoryNameCells);
        List<String> names = new ArrayList<>();
        for (WebElement cell : cells) {
            names.add(cell.getText().trim());
        }
        return names;
    }
    public boolean isEditOptionVisible() {
        // Check if any edit buttons are found on the page
        return !driver.findElements(editButtons).isEmpty();
    }
}