//package user.ui.pages;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.ui.*;
//
//import java.time.Duration;
//
//public class UserPlantListPage {
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    public UserPlantListPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    private By plantsMenu = By.cssSelector("[data-testid='nav-plants']");
//    private By plantsTable = By.cssSelector("[data-testid='plant-table']");
//    private By emptyState = By.cssSelector("[data-testid='plant-empty']");
//    private By resetBtn = By.cssSelector("[data-testid='plant-reset-btn']");
//    private By nextPageBtn = By.cssSelector("[data-testid='pagination-next']");
//
//    private By nameHeader = By.cssSelector("[data-testid='sort-name']");
//    private By priceHeader = By.cssSelector("[data-testid='sort-price']");
//    private By stockHeader = By.cssSelector("[data-testid='sort-stock']");
//
//    public void goToPlantsPage() {
//        wait.until(ExpectedConditions.elementToBeClickable(plantsMenu)).click();
//    }
//
//    public void waitForTable() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(plantsTable));
//    }
//
//    public void sortByName() { wait.until(ExpectedConditions.elementToBeClickable(nameHeader)).click(); }
//    public void sortByPrice() { wait.until(ExpectedConditions.elementToBeClickable(priceHeader)).click(); }
//    public void sortByStock() { wait.until(ExpectedConditions.elementToBeClickable(stockHeader)).click(); }
//
//    public boolean isLowBadgeVisible() {
//        return driver.findElements(By.cssSelector("[data-testid='plant-low-badge']")).size() > 0;
//    }
//
//    public void goNextPage() {
//        wait.until(ExpectedConditions.elementToBeClickable(nextPageBtn)).click();
//        waitForTable();
//    }
//
//    public boolean isEmptyStateVisible() {
//        return driver.findElements(emptyState).size() > 0 && driver.findElement(emptyState).isDisplayed();
//    }
//
//    public void clickReset() {
//        wait.until(ExpectedConditions.elementToBeClickable(resetBtn)).click();
//        waitForTable();
//    }
//}


package user.ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class UserPlantListPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String baseUrl;

    public UserPlantListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        this.baseUrl = System.getProperty("baseUrl", "http://localhost:8008");
    }

    private final By plantsSideMenu = By.xpath("//a[normalize-space()='Plants' or .//*[contains(.,'Plants')]]");
    private final By plantsTable = By.cssSelector("table.table.table-striped.table-bordered.align-middle");

    private final By resetBtn = By.xpath("//button[normalize-space()='Reset']");
    private final By searchInput = By.cssSelector("input[placeholder='Search plant']");
    private final By searchBtn = By.xpath("//button[normalize-space()='Search']");
    private final By lowBadge = By.xpath("//span[contains(@class,'badge') and normalize-space()='Low']");

    // Sort headers (click on <th>)
    private final By nameHeader = By.xpath("//table[contains(@class,'table-striped')]//thead//th[contains(.,'Name')]");
    private final By priceHeader = By.xpath("//table[contains(@class,'table-striped')]//thead//th[contains(.,'Price')]");
    private final By stockHeader = By.xpath("//table[contains(@class,'table-striped')]//thead//th[contains(.,'Stock')]");

    // Pagination - best effort
    private final By nextPage = By.cssSelector("ul.pagination li.page-item:not(.disabled) a.page-link[aria-label='Next'], ul.pagination li.page-item:not(.disabled) a.page-link");

    private By table = By.cssSelector("table.table.table-striped.table-bordered.align-middle");
    private By rows = By.cssSelector("table.table.table-striped.table-bordered.align-middle tbody tr");


    private By categorySelect = By.cssSelector("select");

    // Things that should NOT exist for User
    private By addPlantLink = By.xpath("//a[contains(normalize-space(),'Add') and contains(normalize-space(),'Plant')]");
    private By editLinks = By.cssSelector("a[title='Edit']");
    private By deleteButtons = By.cssSelector("button[title='Delete'], .btn-outline-danger");

    public void goToPlantsPage() {
        if (driver.getCurrentUrl() == null || !driver.getCurrentUrl().contains("/ui/plants")) {
            wait.until(ExpectedConditions.elementToBeClickable(plantsSideMenu)).click();
        }
        waitForTable();
    }

    public void waitForTable() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(plantsTable));
    }

    public boolean isTableDisplayed() {
        try {
            return driver.findElement(table).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

//    public boolean isAddPlantVisible() {
//        try {
//            return driver.findElement(addPlantLink).isDisplayed();
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }
public boolean isAddPlantVisible() {
    By addPlantBtn = By.xpath("//a[contains(normalize-space(),'Add') and contains(normalize-space(),'Plant')]");

    // ✅ safest way: findElements never throws NoSuchElementException
    List<WebElement> els = driver.findElements(addPlantBtn);

    if (els.isEmpty()) return false;      // not present -> NOT visible
    return els.get(0).isDisplayed();      // present -> check visible
}


    public boolean areEditDeleteVisible() {
        // If any edit/delete exists -> true (should be false for user)
        return !driver.findElements(editLinks).isEmpty() || !driver.findElements(deleteButtons).isEmpty();
    }

    public void setSearchKeyword(String keyword) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        el.clear();
        el.sendKeys(keyword);
    }

    public void clickSearch() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        el.click();
        waitForTable();
    }


    public void selectCategoryFilterByVisibleText(String text) {
        WebElement selectEl = wait.until(ExpectedConditions.visibilityOfElementLocated(categorySelect));
        new Select(selectEl).selectByVisibleText(text);
    }

    public List<String> getAllCategoryFilterOptions() {
        WebElement selectEl = wait.until(ExpectedConditions.visibilityOfElementLocated(categorySelect));
        Select sel = new Select(selectEl);
        List<String> list = new ArrayList<>();
        sel.getOptions().forEach(o -> list.add(o.getText().trim()));
        return list;
    }



    public void clickSortByName() {
        wait.until(ExpectedConditions.elementToBeClickable(nameHeader)).click();
        waitForTable();
    }

    public void clickSortByPrice() {
        wait.until(ExpectedConditions.elementToBeClickable(priceHeader)).click();
        waitForTable();
    }

    public void clickSortByStock() {
        wait.until(ExpectedConditions.elementToBeClickable(stockHeader)).click();
        waitForTable();
    }

    public boolean isLowBadgeVisible() {
        return driver.findElements(lowBadge).size() > 0;
    }

    public void goNextPageIfPossible() {
        List<WebElement> next = driver.findElements(nextPage);
        if (!next.isEmpty() && next.get(0).isDisplayed()) {
            next.get(0).click();
            waitForTable();
        }
    }

    public List<String> getColumnTexts(int colIndex1Based) {
        waitForTable();
        List<WebElement> cells = driver.findElements(
                By.cssSelector("table.table.table-striped.table-bordered.align-middle tbody tr td:nth-of-type(" + colIndex1Based + ")")
        );
        return cells.stream().map(e -> e.getText().trim()).collect(Collectors.toList());
    }

    public void search(String keyword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput)).clear();
        driver.findElement(searchInput).sendKeys(keyword);
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        waitForTable();
    }

//    public void clickReset() {
//        wait.until(ExpectedConditions.elementToBeClickable(resetBtn)).click();
//        waitForTable();
//    }
public void clickReset() {
    // Reset is an <a> tag, not a <button>
//    By resetLink = By.cssSelector("a.btn.btn-outline-secondary[href='/ui/plants']");
    By resetLink = By.xpath("//a[normalize-space()='Reset']"); // safe locator

    WebElement el = wait.until(ExpectedConditions.elementToBeClickable(resetLink));

    // optional but helps if element is slightly off-screen
    ((org.openqa.selenium.JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollIntoView({block:'center'});", el);

    el.click();

    // Wait until page resets back to /ui/plants and table is visible again
    wait.until(ExpectedConditions.urlContains("/ui/plants"));
    wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("table.table.table-striped.table-bordered.align-middle")));
}


    public boolean isNoResultsShown() {
        List<WebElement> rows = driver.findElements(By.cssSelector("table.table.table-striped.table-bordered.align-middle tbody tr"));
        if (rows.isEmpty()) return true;

        By msg = By.xpath("//*[contains(normalize-space(),'No plants found') or contains(normalize-space(),'No data')]");
        return driver.findElements(msg).size() > 0;
    }

    public boolean isAlphabeticallySorted(List<String> values) {
        List<String> cleaned = values.stream().filter(s -> !s.isBlank()).collect(Collectors.toList());
        if (cleaned.size() < 2) return true;

        List<String> sorted = new ArrayList<>(cleaned);
        sorted.sort(String.CASE_INSENSITIVE_ORDER);
        return cleaned.equals(sorted);
    }
}
