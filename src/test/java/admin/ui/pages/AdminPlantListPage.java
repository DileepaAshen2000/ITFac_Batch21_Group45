//package admin.ui.pages;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.ui.*;
//
//import java.time.Duration;
//
//public class AdminPlantListPage {
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    public AdminPlantListPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    private By plantsMenu = By.cssSelector("[data-testid='nav-plants']");
//    private By plantsTable = By.cssSelector("[data-testid='plant-table']");
//    private By addPlantBtn = By.cssSelector("[data-testid='add-plant-btn']");
//
//    public void goToPlantsPage() {
//        wait.until(ExpectedConditions.elementToBeClickable(plantsMenu)).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(plantsTable));
//    }
//
//    public boolean isEditVisibleForRow(int rowIndex) {
//        By editBtn = By.cssSelector("[data-testid='plant-row-" + rowIndex + "'] [data-testid='plant-edit-btn']");
//        return driver.findElements(editBtn).size() > 0 && driver.findElement(editBtn).isDisplayed();
//    }
//
//    public boolean isDeleteVisibleForRow(int rowIndex) {
//        By deleteBtn = By.cssSelector("[data-testid='plant-row-" + rowIndex + "'] [data-testid='plant-delete-btn']");
//        return driver.findElements(deleteBtn).size() > 0 && driver.findElement(deleteBtn).isDisplayed();
//    }
//
//    public void clickEditForRow(int rowIndex) {
//        By editBtn = By.cssSelector("[data-testid='plant-row-" + rowIndex + "'] [data-testid='plant-edit-btn']");
//        wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
//    }
//
//    public void clickDeleteForRow(int rowIndex) {
//        By deleteBtn = By.cssSelector("[data-testid='plant-row-" + rowIndex + "'] [data-testid='plant-delete-btn']");
//        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
//    }
//
//    public void clickAddPlant() {
//        wait.until(ExpectedConditions.elementToBeClickable(addPlantBtn)).click();
//    }
//
//    public boolean isPlantPresentByName(String name) {
//        By row = By.xpath("//table[@data-testid='plant-table']//tr[td[contains(.,'" + name + "')]]");
//        return driver.findElements(row).size() > 0;
//    }
//}


package admin.ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class AdminPlantListPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public AdminPlantListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(12));
    }

    // From your screenshot: url has /ui/plants
    private final By plantsSideMenu = By.xpath("//a[normalize-space()='Plants' or .//*[contains(.,'Plants')]]");

    // From Elements panel: <table class="table table-striped table-bordered align-middle">
    private final By plantsTable = By.cssSelector("table.table.table-striped.table-bordered.align-middle");

    // Search area
    private final By searchPlantInput = By.cssSelector("input[placeholder='Search plant']");
    private final By searchBtn = By.xpath("//button[normalize-space()='Search']");
    private final By resetBtn = By.xpath("//button[normalize-space()='Reset']");

    // Add Plant button
    private final By addPlantBtn = By.xpath("//button[contains(.,'Add') and contains(.,'Plant')]");

    // Pagination (generic bootstrap style)
    private final By paginationNext = By.cssSelector("ul.pagination li.page-item:not(.disabled) a.page-link[aria-label='Next'], ul.pagination li.page-item:not(.disabled) a.page-link");

    // Low badge (red badge "Low")
    private final By lowBadge = By.xpath("//span[contains(@class,'badge') and normalize-space()='Low']");

    public void goToPlantsPage() {
        // If your system already lands on /ui/plants after login, this still works.
        if (driver.getCurrentUrl() == null || !driver.getCurrentUrl().contains("/ui/plants")) {
            wait.until(ExpectedConditions.elementToBeClickable(plantsSideMenu)).click();
        }
        waitForTableVisible();
    }

    public void waitForTableVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(plantsTable));
    }

    public boolean isTableDisplayed() {
        return driver.findElements(plantsTable).size() > 0 && driver.findElement(plantsTable).isDisplayed();
    }

    private By getRow(int rowIndex1Based) {
        return By.cssSelector("table.table.table-striped.table-bordered.align-middle tbody tr:nth-of-type(" + rowIndex1Based + ")");
    }

    // Actions column: last cell contains two buttons (Edit then Delete)
    private By getEditButtonInRow(int rowIndex1Based) {
        return By.cssSelector("table.table.table-striped.table-bordered.align-middle tbody tr:nth-of-type(" + rowIndex1Based + ") td:last-child button:nth-of-type(1)");
    }

    private By getDeleteButtonInRow(int rowIndex1Based) {
        return By.cssSelector("table.table.table-striped.table-bordered.align-middle tbody tr:nth-of-type(" + rowIndex1Based + ") td:last-child button:nth-of-type(2)");
    }

    public boolean isEditVisibleForFirstRow() {
        waitForTableVisible();
        return driver.findElements(getEditButtonInRow(1)).size() > 0 && driver.findElement(getEditButtonInRow(1)).isDisplayed();
    }

//    public boolean isDeleteVisibleForFirstRow() {
//        waitForTableVisible();
//        return driver.findElements(getDeleteButtonInRow(1)).size() > 0 && driver.findElement(getDeleteButtonInRow(1)).isDisplayed();
//    }
public boolean isDeleteVisibleForFirstRow() {
    waitForTableVisible();

    By deleteBtn = By.cssSelector(
            "table.table.table-striped.table-bordered.align-middle tbody tr:nth-of-type(1) td:last-child button[title='Delete']"
    );

    try {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(deleteBtn)).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

    public String getFirstRowPlantName() {
        waitForTableVisible();
        By firstNameCell = By.cssSelector("table.table.table-striped.table-bordered.align-middle tbody tr:nth-of-type(1) td:nth-of-type(1)");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameCell)).getText().trim();
    }

//    public void clickEditFirstRow() {
//        waitForTableVisible();
//        wait.until(ExpectedConditions.elementToBeClickable(getEditButtonInRow(1))).click();
//    }
public void clickEditFirstRow() {
    waitForTableVisible();

    By editBtn = By.cssSelector(
            "table.table.table-striped.table-bordered.align-middle tbody tr:nth-of-type(1) td:last-child a[title='Edit']"
    );

    WebElement edit = wait.until(ExpectedConditions.elementToBeClickable(editBtn));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", edit);
    edit.click();
}


//    public void clickDeleteFirstRow() {
//        waitForTableVisible();
//        wait.until(ExpectedConditions.elementToBeClickable(getDeleteButtonInRow(1))).click();
//    }

//    public void clickDeleteFirstRow() {
//        waitForTableVisible();
//
////        By deleteBtn = By.cssSelector(
////                "table.table.table-striped.table-bordered.align-middle tbody tr:nth-of-type(1) td:last-child button:nth-of-type(2)"
////        );
//        By deleteBtn = By.cssSelector(
//                "table.table.table-striped.table-bordered.align-middle tbody tr:nth-of-type(1) td:last-child .btn-danger"
//        );
//
//
//        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
//
//        // ✅ Handle JS confirm alert: "Delete this plant?"
//        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
//        alert.accept();
//
//        // Wait table refresh after deletion
//        waitForTableVisible();
//    }

    public void clickDeleteFirstRow() {
        waitForTableVisible();

        // 1) Wait for first row
        By firstRowBy = By.cssSelector("table.table.table-striped.table-bordered.align-middle tbody tr:nth-of-type(1)");
        WebElement firstRow = wait.until(ExpectedConditions.visibilityOfElementLocated(firstRowBy));

        // 2) Actions cell (last td)
        WebElement actionsCell = firstRow.findElement(By.cssSelector("td:last-child"));

        // 3) Collect possible action controls (button or a)
        List<WebElement> actionControls = actionsCell.findElements(By.cssSelector("button, a"));

        if (actionControls.isEmpty()) {
            throw new NoSuchElementException("No action buttons found in first row Actions column");
        }

        // 4) Pick delete button:
        // Prefer element with class containing "danger" (btn-danger / btn-outline-danger)
        WebElement deleteBtn = null;
        for (WebElement el : actionControls) {
            String cls = (el.getAttribute("class") == null) ? "" : el.getAttribute("class").toLowerCase();
            String title = (el.getAttribute("title") == null) ? "" : el.getAttribute("title").toLowerCase();
            String aria = (el.getAttribute("aria-label") == null) ? "" : el.getAttribute("aria-label").toLowerCase();
            String text = el.getText().toLowerCase();

            if (cls.contains("danger") || title.contains("delete") || aria.contains("delete") || text.contains("delete")) {
                deleteBtn = el;
                break;
            }
        }

        // Fallback: usually delete is the last control in actions cell
        if (deleteBtn == null) {
            deleteBtn = actionControls.get(actionControls.size() - 1);
        }

        // 5) Scroll into view and click (with JS fallback)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", deleteBtn);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);
        }

        // 6) Handle JS confirm alert: "Delete this plant?"
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());
        alert.accept();

        // 7) Wait table refresh (row becomes stale OR table visible)
        wait.until(ExpectedConditions.stalenessOf(firstRow));
        waitForTableVisible();
    }



//    public void clickAddPlant() {
//        wait.until(ExpectedConditions.elementToBeClickable(addPlantBtn)).click();
//    }

    public void clickAddPlant() {
        waitForTableVisible();

        By addPlantLink = By.xpath("//a[normalize-space()='Add a Plant']");
        wait.until(ExpectedConditions.elementToBeClickable(addPlantLink)).click();
    }

    public void searchPlant(String keyword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchPlantInput)).clear();
        driver.findElement(searchPlantInput).sendKeys(keyword);
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        waitForTableVisible();
    }

    public void resetSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(resetBtn)).click();
        waitForTableVisible();
    }

    public boolean isLowBadgeVisible() {
        return driver.findElements(lowBadge).size() > 0;
    }

    public boolean isPlantPresentByName(String name) {
        waitForTableVisible();
        By row = By.xpath("//table[contains(@class,'table-striped')]//tbody//tr[td[1][normalize-space()='" + name + "']]");
        return driver.findElements(row).size() > 0;
    }

    public int getRowCount() {
        waitForTableVisible();
        List<WebElement> rows = driver.findElements(By.cssSelector("table.table.table-striped.table-bordered.align-middle tbody tr"));
        return rows.size();
    }

    public boolean isNoResultsShown() {
        // Some apps show empty tbody or a message. We'll support both.
        if (getRowCount() == 0) return true;

        // or a "No plants found" message (if exists)
        By noMsg = By.xpath("//*[contains(normalize-space(),'No plants found') or contains(normalize-space(),'No data')]");
        return driver.findElements(noMsg).size() > 0;
    }

    public void goNextPageIfPossible() {
        List<WebElement> next = driver.findElements(paginationNext);
        if (!next.isEmpty() && next.get(0).isDisplayed()) {
            next.get(0).click();
            waitForTableVisible();
        }
    }
}
