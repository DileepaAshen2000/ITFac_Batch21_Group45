package admin.ui.pages;

import common.utils.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SellPlantPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By plantSelect = By.xpath("//select[contains(@name,'plant') or contains(@id,'plant') or contains(@name,'plantId') or contains(@id,'plantId')] | //select");
    private final By quantityInput = By.xpath("//input[@type='number' or contains(@name,'quantity') or contains(@id,'quantity')]");
    private final By sellButton = By.xpath(
            "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sell')]" +
            " | //input[@type='submit' and (contains(@value,'Sell') or contains(@value,'sell'))]"
    );

    private final By successMessage = By.xpath(
            "//*[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'success')]" +
            " | //div[contains(@class,'alert') and contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'success')]"
    );

    private final By validationError = By.xpath(
            "//*[contains(@class,'error') or contains(@class,'invalid') or contains(@class,'validation')]" +
            "[string-length(normalize-space(.))>0]" +
            " | //small[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'required')]" +
            " | //span[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'required')]"
    );

    public SellPlantPage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open(String path) {
        driver.get("http://localhost:8008" + path);
        wait.until(ExpectedConditions.urlContains(path));
    }

    public boolean isOnPage(String path) {
        try {
            return wait.until(ExpectedConditions.urlContains(path));
        } catch (Exception e) {
            return false;
        }
    }

    public void openPlantDropdown() {
        WebElement selectEl = wait.until(ExpectedConditions.presenceOfElementLocated(plantSelect));
        selectEl.click();
    }

    public List<String> getPlantOptionsText() {
        WebElement selectEl = wait.until(ExpectedConditions.presenceOfElementLocated(plantSelect));
        Select select = new Select(selectEl);
        List<String> texts = new ArrayList<>();
        for (WebElement opt : select.getOptions()) {
            String t = opt.getText();
            if (t != null) texts.add(t.trim());
        }
        return texts;
    }

    public void selectFirstPlantOption() {
        WebElement selectEl = wait.until(ExpectedConditions.presenceOfElementLocated(plantSelect));
        Select select = new Select(selectEl);

        // skip placeholder like "Select..."
        for (WebElement opt : select.getOptions()) {
            if (!opt.isEnabled()) continue;
            String t = (opt.getText() == null) ? "" : opt.getText().trim().toLowerCase();
            if (t.isEmpty()) continue;
            if (t.contains("select")) continue;
            select.selectByVisibleText(opt.getText());
            return;
        }
        // if nothing, try index 0
        select.selectByIndex(0);
    }

    public void setQuantity(int qty) {
        WebElement qtyEl = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
        qtyEl.clear();
        qtyEl.sendKeys(String.valueOf(qty));
    }

    public void clearPlantSelectionIfPossible() {
        try {
            WebElement selectEl = wait.until(ExpectedConditions.presenceOfElementLocated(plantSelect));
            Select select = new Select(selectEl);
            select.selectByIndex(0); // common placeholder
        } catch (Exception ignored) {}
    }

    public void clearQuantity() {
        try {
            WebElement qtyEl = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
            qtyEl.clear();
        } catch (Exception ignored) {}
    }

    public void clickSell() {
        wait.until(ExpectedConditions.elementToBeClickable(sellButton)).click();
    }

    public boolean isSuccessMessageVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasValidationErrors() {
        try {
            return !driver.findElements(validationError).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
