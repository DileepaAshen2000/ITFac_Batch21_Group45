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
            By.xpath("//a[contains(text(),'Add')]");

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
            By.xpath("//*[contains(text(),'No category') or contains(text(),'No data')]");

    private By tableBody =
            By.xpath("//table/tbody");

    private By categoryDataRows =
            By.xpath("//table/tbody/tr[td[not(@colspan)]]");

    public UserCategoryListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ========= SORTING =========

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
            } catch (NumberFormatException ignored) {
            }
        }
        return ids;
    }

    // ========= ADD BUTTON =========

    public boolean isAddCategoryButtonVisible() {
        return !driver.findElements(addCategoryButton).isEmpty();
    }

    // ========= EDIT / DELETE =========

    public boolean hasEnabledEditButtons() {
        for (WebElement btn : driver.findElements(editButtons)) {
            if (btn.getAttribute("disabled") == null) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEnabledDeleteButtons() {
        for (WebElement btn : driver.findElements(deleteButtons)) {
            if (btn.getAttribute("disabled") == null) {
                return true;
            }
        }
        return false;
    }

    // ========= PAGINATION =========

    public void clickNextButton() {
        WebElement next = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", next);
        wait.until(ExpectedConditions.presenceOfElementLocated(tableBody));
    }

    public void clickPreviousButton() {
        WebElement prev = wait.until(ExpectedConditions.elementToBeClickable(previousButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", prev);
        wait.until(ExpectedConditions.presenceOfElementLocated(tableBody));
    }

    public int getCurrentPageNumber() {
        return Integer.parseInt(
                wait.until(ExpectedConditions.visibilityOfElementLocated(activePage))
                        .getText().trim()
        );
    }

    public boolean isPaginationVisible() {
        return !driver.findElements(paginationContainer).isEmpty();
    }

    // ========= EMPTY STATE =========

    public boolean isNoCategoryMessageDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(noCategoryMessage)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getNoCategoryMessageText() {
        try {
            return driver.findElement(noCategoryMessage).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean hasTableRows() {
        return !driver.findElements(categoryDataRows).isEmpty();
    }
}
