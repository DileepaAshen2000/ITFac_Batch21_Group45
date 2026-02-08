package admin.ui.pages;

import common.utils.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SalesListPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // very flexible locators (project UI may differ)
    private final By salesTable = By.cssSelector("table");
    private final By sellPlantButton = By.xpath(
            "//a[contains(@href,'/ui/sales/new') or contains(@href,'/ui/sales/add')]" +
            " | //button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sell') and contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'plant')]" +
            " | //a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sell') and contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'plant')]"
    );

    private final By noSalesMessage = By.xpath(
            "//*[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'no sales') and contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'found')]"
    );

    public SalesListPage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open(String path) {
        driver.get("http://localhost:8008" + path);
        wait.until(ExpectedConditions.urlContains(path));
    }

    public boolean isSalesListVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(salesTable)).isDisplayed();
        } catch (Exception e) {
            // fallback: allow "No Sales Found"
            try {
                return driver.findElement(noSalesMessage).isDisplayed();
            } catch (Exception ignored) {
                return false;
            }
        }
    }

    public boolean isSellPlantButtonVisible() {
        try {
            return driver.findElement(sellPlantButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSellPlantButtonPresent() {
        return !driver.findElements(sellPlantButton).isEmpty();
    }

    public void clickSellPlant() {
        wait.until(ExpectedConditions.elementToBeClickable(sellPlantButton)).click();
    }

    public int getSalesRowCount() {
        try {
            WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(salesTable));
            List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
            if (rows.isEmpty()) {
                // some tables don't have tbody
                rows = table.findElements(By.cssSelector("tr"));
            }
            // remove header rows if present
            int count = 0;
            for (WebElement r : rows) {
                if (!r.findElements(By.cssSelector("th")).isEmpty()) continue;
                count++;
            }
            return count;
        } catch (Exception e) {
            return 0;
        }
    }

    public WebElement findFirstDeleteButton() {
        // Try common patterns: button/link with text Delete or icon title/aria-label
        List<WebElement> candidates = driver.findElements(By.xpath(
                "//table//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'delete')]" +
                " | //table//a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'delete')]" +
                " | //table//*[@aria-label='Delete' or @title='Delete' or contains(@class,'delete')]"
        ));
        return candidates.isEmpty() ? null : candidates.get(0);
    }

    public boolean isNoSalesMessageVisible() {
        try {
            return driver.findElement(noSalesMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
