
package admin.ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AdminPlantListPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String baseUrl;

    public AdminPlantListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        this.baseUrl = System.getProperty("baseUrl", "http://localhost:8008");
    }
    private By table = By.cssSelector("table.table.table-striped.table-bordered.align-middle");
    private By tableBodyRows = By.cssSelector("table.table.table-striped.table-bordered.align-middle tbody tr");
    private By searchInput = By.cssSelector("input[placeholder='Search plant']");
    private By categorySelect = By.cssSelector("select");

    private By addPlantLink = By.xpath("//a[contains(normalize-space(),'Add') and contains(normalize-space(),'Plant')]");
    private final By plantsSideMenu = By.xpath("//a[normalize-space()='Plants' or .//*[contains(.,'Plants')]]");

    private final By plantsTable = By.cssSelector("table.table.table-striped.table-bordered.align-middle");


    private final By searchPlantInput = By.cssSelector("input[placeholder='Search plant']");
    private final By searchBtn = By.xpath("//button[normalize-space()='Search']");
    private final By resetBtn = By.xpath("//button[normalize-space()='Reset']");


    private final By addPlantBtn = By.xpath("//button[contains(.,'Add') and contains(.,'Plant')]");


    private final By paginationNext = By.cssSelector("ul.pagination li.page-item:not(.disabled) a.page-link[aria-label='Next'], ul.pagination li.page-item:not(.disabled) a.page-link");


    private final By lowBadge = By.xpath("//span[contains(@class,'badge') and normalize-space()='Low']");

    public void goToPlantsPage() {

        if (driver.getCurrentUrl() == null || !driver.getCurrentUrl().contains("/ui/plants")) {
            wait.until(ExpectedConditions.elementToBeClickable(plantsSideMenu)).click();
        }
        waitForTableVisible();
    }

    public boolean isAddPlantVisible() {
        try {
            return driver.findElement(addPlantLink).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void setSearchKeyword(String keyword) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        el.clear();
        el.sendKeys(keyword);
    }

    public void clickSearch() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        el.click();
        waitForTableVisible();
    }

    public void clickReset() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(resetBtn));
        el.click();
        waitForTableVisible();
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

    public List<String> getColumnTexts(int tdIndex1Based) {
        waitForTableVisible();
        List<WebElement> rows = driver.findElements(tableBodyRows);
        List<String> vals = new ArrayList<>();
        for (WebElement r : rows) {
            List<WebElement> tds = r.findElements(By.cssSelector("td"));
            if (tds.size() >= tdIndex1Based) {
                vals.add(tds.get(tdIndex1Based - 1).getText().trim());
            }
        }
        return vals;
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


public void clickEditFirstRow() {
    waitForTableVisible();

    By editBtn = By.cssSelector(
            "table.table.table-striped.table-bordered.align-middle tbody tr:nth-of-type(1) td:last-child a[title='Edit']"
    );

    WebElement edit = wait.until(ExpectedConditions.elementToBeClickable(editBtn));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", edit);
    edit.click();
}


    public void clickDeleteFirstRow() {
        waitForTableVisible();

        By firstRowBy = By.cssSelector("table.table.table-striped.table-bordered.align-middle tbody tr:nth-of-type(1)");
        WebElement firstRow = wait.until(ExpectedConditions.visibilityOfElementLocated(firstRowBy));


        WebElement actionsCell = firstRow.findElement(By.cssSelector("td:last-child"));

        List<WebElement> actionControls = actionsCell.findElements(By.cssSelector("button, a"));

        if (actionControls.isEmpty()) {
            throw new NoSuchElementException("No action buttons found in first row Actions column");
        }

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


        if (deleteBtn == null) {
            deleteBtn = actionControls.get(actionControls.size() - 1);
        }

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", deleteBtn);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);
        }

        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());
        alert.accept();

        wait.until(ExpectedConditions.stalenessOf(firstRow));
        waitForTableVisible();
    }



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
        if (getRowCount() == 0) return true;

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
