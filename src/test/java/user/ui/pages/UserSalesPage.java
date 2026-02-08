package user.ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UserSalesPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By salesTable = By.cssSelector("table");
    private final By sellPlantButton = By.xpath(
            "//a[contains(@href,'/ui/sales/new')]" +
            " | //button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sell') and contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'plant')]" +
            " | //a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sell') and contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'plant')]"
    );
    private final By deleteButton = By.xpath(
            "//table//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'delete')]" +
            " | //table//a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'delete')]" +
            " | //table//*[@aria-label='Delete' or @title='Delete' or contains(@class,'delete')]"
    );

    private final By noSalesMessage = By.xpath(
            "//*[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'no sales') and contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'found')]"
    );

    public UserSalesPage(WebDriver driver) {
        this.driver = driver;
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
            try {
                return driver.findElement(noSalesMessage).isDisplayed();
            } catch (Exception ignored) {
                return false;
            }
        }
    }

    public boolean isSellPlantButtonPresent() {
        return !driver.findElements(sellPlantButton).isEmpty();
    }

    public boolean isDeleteButtonPresent() {
        return !driver.findElements(deleteButton).isEmpty();
    }

    public void clickQuantityHeader() {
        // try header with text Quantity
        List<WebElement> headers = driver.findElements(By.xpath(
                "//table//th[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'quantity')]" +
                " | //table//a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'quantity')]"
        ));
        if (!headers.isEmpty()) {
            headers.get(0).click();
        }
    }

    public List<Integer> readQuantityColumn() {
        List<Integer> values = new ArrayList<>();
        try {
            WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(salesTable));
            List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
            if (rows.isEmpty()) rows = table.findElements(By.cssSelector("tr"));
            for (WebElement r : rows) {
                if (!r.findElements(By.cssSelector("th")).isEmpty()) continue;

                List<WebElement> tds = r.findElements(By.cssSelector("td"));
                for (WebElement td : tds) {
                    String txt = (td.getText() == null) ? "" : td.getText().trim();
                    if (txt.matches("\\d+")) {
                        values.add(Integer.parseInt(txt));
                        break;
                    }
                }
            }
        } catch (Exception ignored) {}
        return values;
    }

    public boolean isNoSalesMessageVisible() {
        try {
            return driver.findElement(noSalesMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
