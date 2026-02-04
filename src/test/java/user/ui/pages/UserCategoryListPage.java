package user.ui.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserCategoryListPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By idHeader =
            By.xpath("//a[contains(@href,'sortField=id')]");

    private By idColumnCells =
            By.xpath("//table/tbody/tr/td[1]");

    private By addCategoryButton =
            By.xpath("//a[contains(text(),'Add A Category')]");

    private By editButtons =
            By.cssSelector("a[title='Edit'], a[tittle='Edit']");

    private By deleteButtons =
            By.cssSelector("button[title='Delete']");

    private By nextButton =
            By.xpath("//a[contains(text(),'Next')]");

    private By previousButton =
            By.xpath("//a[contains(text(),'Previous')]");

    private By activePage =
            By.xpath("//li[@class='page-item active']//a[@class='page-link']");

    private By paginationContainer =
            By.cssSelector("ul.pagination");

    private By noCategoryMessage =
            By.xpath("//table/tbody/tr/td[@colspan='4' and contains(@class, 'text-center')]");

    private By tableBody =
            By.xpath("//table/tbody");

    private By categoryDataRows =
            By.xpath("//table/tbody/tr[td[not(@colspan)]]");

    public UserCategoryListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ========== BASIC METHODS (for TC_01 sorting) ==========

    public void clickIdHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(idHeader))
                .click();
    }

    public List<Integer> getCategoryIds() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(idColumnCells));

        List<Integer> ids = new ArrayList<>();
        List<WebElement> cells = driver.findElements(idColumnCells);

        for (WebElement cell : cells) {
            try {
                ids.add(Integer.parseInt(cell.getText().trim()));
            } catch (NumberFormatException e) {
                // Skip non-numeric cells (like empty message)
                continue;
            }
        }
        return ids;
    }

    // ========== ADD CATEGORY BUTTON (for TC_02) ==========

    public boolean isAddCategoryButtonVisible() {
        return !driver.findElements(addCategoryButton).isEmpty();
    }

    // ========== EDIT/DELETE BUTTONS (for TC_03) ==========

    public boolean hasEnabledEditButtons() {
        List<WebElement> editBtns = driver.findElements(editButtons);

        for (WebElement btn : editBtns) {
            String disabledAttr = btn.getAttribute("disabled");
            if (disabledAttr == null || disabledAttr.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEnabledDeleteButtons() {
        List<WebElement> deleteBtns = driver.findElements(deleteButtons);

        for (WebElement btn : deleteBtns) {
            String disabledAttr = btn.getAttribute("disabled");
            if (disabledAttr == null || disabledAttr.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    // ========== PAGINATION METHODS (for TC_04) ==========

    public void clickNextButton() {
        WebElement next = wait.until(ExpectedConditions.elementToBeClickable(nextButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", next);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            next.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", next);
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(tableBody));
    }

    public void clickPreviousButton() {
        WebElement previous = wait.until(ExpectedConditions.elementToBeClickable(previousButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", previous);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            previous.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", previous);
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(tableBody));
    }

    public int getCurrentPageNumber() {
        WebElement activePageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(activePage)
        );
        String pageText = activePageElement.getText().trim();
        return Integer.parseInt(pageText);
    }

    public boolean isPaginationVisible() {
        return !driver.findElements(paginationContainer).isEmpty();
    }

    public boolean isNextButtonEnabled() {
        List<WebElement> nextButtons = driver.findElements(nextButton);
        if (nextButtons.isEmpty()) {
            return false;
        }

        WebElement nextLi = nextButtons.get(0).findElement(By.xpath(".."));
        String liClass = nextLi.getAttribute("class");

        return !liClass.contains("disabled");
    }

    public boolean isPreviousButtonEnabled() {
        List<WebElement> prevButtons = driver.findElements(previousButton);
        if (prevButtons.isEmpty()) {
            return false;
        }

        WebElement prevLi = prevButtons.get(0).findElement(By.xpath(".."));
        String liClass = prevLi.getAttribute("class");

        return !liClass.contains("disabled");
    }

    // ========== EMPTY STATE METHODS (for TC_05) ==========

    public boolean isNoCategoryMessageDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(noCategoryMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getNoCategoryMessageText() {
        try {
            WebElement messageElement = driver.findElement(noCategoryMessage);
            return messageElement.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean hasTableRows() {
        List<WebElement> dataRows = driver.findElements(categoryDataRows);
        return !dataRows.isEmpty();
    }

    public boolean isTableBodyPresent() {
        return !driver.findElements(tableBody).isEmpty();
    }
}
