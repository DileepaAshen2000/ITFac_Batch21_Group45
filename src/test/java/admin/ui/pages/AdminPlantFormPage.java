////package admin.ui.pages;
////
////import org.openqa.selenium.*;
////import org.openqa.selenium.support.ui.*;
////
////import java.time.Duration;
////
////public class AdminPlantFormPage {
////    private WebDriver driver;
////    private WebDriverWait wait;
////
////    public AdminPlantFormPage(WebDriver driver) {
////        this.driver = driver;
////        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
////    }
////
////    private By nameInput = By.cssSelector("[data-testid='plant-name']");
////    private By priceInput = By.cssSelector("[data-testid='plant-price']");
////    private By qtyInput = By.cssSelector("[data-testid='plant-quantity']");
////    private By categorySelect = By.cssSelector("[data-testid='plant-category']");
////    private By saveBtn = By.cssSelector("[data-testid='plant-save-btn']");
////    private By cancelBtn = By.cssSelector("[data-testid='plant-cancel-btn']");
////
////    public void updatePlant(String name, String price, String qty, String category) {
////        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).clear();
////        driver.findElement(nameInput).sendKeys(name);
////
////        driver.findElement(priceInput).clear();
////        driver.findElement(priceInput).sendKeys(price);
////
////        driver.findElement(qtyInput).clear();
////        driver.findElement(qtyInput).sendKeys(qty);
////
////        new Select(driver.findElement(categorySelect)).selectByVisibleText(category);
////    }
////
////    public void clickSave() {
////        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
////    }
////
////    public void clickCancel() {
////        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
////    }
////}
//
//
//package admin.ui.pages;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.ui.*;
//
//import java.time.Duration;
//
//public class AdminPlantFormPage {
//
//    private final WebDriver driver;
//    private final WebDriverWait wait;
//
//    public AdminPlantFormPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(12));
//    }
//
//    // We don't have testids, so use label-based robust locators:
//    private final By nameInput = By.xpath("//label[contains(.,'Name')]/following::input[1]");
//    private final By priceInput = By.xpath("//label[contains(.,'Price')]/following::input[1]");
//    private final By stockInput = By.xpath("//label[contains(.,'Stock') or contains(.,'Quantity')]/following::input[1]");
//    private final By categorySelect = By.xpath("//label[contains(.,'Category')]/following::select[1]");
//
//    private final By saveBtn = By.xpath("//button[normalize-space()='Save' or normalize-space()='Update']");
//    private final By cancelBtn = By.xpath("//button[normalize-space()='Cancel']");
//
//    public void waitForFormVisible() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelBtn));
//    }
//
//    public void setName(String name) {
//        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
//        el.clear();
//        el.sendKeys(name);
//    }
//
//    public void setPrice(String price) {
//        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(priceInput));
//        el.clear();
//        el.sendKeys(price);
//    }
//
//    public void setStock(String stock) {
//        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(stockInput));
//        el.clear();
//        el.sendKeys(stock);
//    }
//
//    public void setCategoryByVisibleText(String category) {
//        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(categorySelect));
//        new Select(el).selectByVisibleText(category);
//    }
//
//    public void clickSave() {
//        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
//    }
//
//    public void clickCancel() {
//        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
//    }
//}

package admin.ui.pages;

import common.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AdminPlantFormPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Page-level locators (stable)
    private final By headingEditPlant = By.xpath("//h3[normalize-space()='Edit Plant' or normalize-space()='Add Plant']");
    private final By saveBtn = By.cssSelector("form button.btn.btn-primary");
    private final By cancelLink = By.cssSelector("form a.btn.btn-secondary[href='/ui/plants']");

    // Field locators (label-based)
    private final By plantNameInput = By.xpath("//label[normalize-space()='Plant Name']/following::input[1]");
    private final By categorySelect = By.xpath("//label[normalize-space()='Category']/following::select[1]");
    private final By priceInput = By.xpath("//label[normalize-space()='Price']/following::input[1]");
    private final By quantityInput = By.xpath("//label[normalize-space()='Quantity']/following::input[1]");

    private By formHeading = By.xpath("//h3[contains(normalize-space(),'Plant')]");
    private By nameInput = By.xpath("//label[normalize-space()='Plant Name']/following-sibling::input");
    private By qtyInput = By.xpath("//label[normalize-space()='Quantity']/following-sibling::input");


    // Common validation styles (covers most server-side validation UIs)
    private By validationErrors = By.cssSelector(
            ".text-danger, .invalid-feedback, .field-validation-error, .alert.alert-danger"
    );

    public AdminPlantFormPage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isFormVisible() {
        try {
            return driver.findElement(formHeading).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectCategoryByIndex(int index) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(categorySelect));
        Select sel = new Select(el);
        sel.selectByIndex(index);
    }

    public boolean hasAnyValidationErrors() {
        // give UI a tiny moment to render errors after submit
        try { Thread.sleep(300); } catch (InterruptedException ignored) {}
        List<WebElement> errs = driver.findElements(validationErrors);
        for (WebElement e : errs) {
            if (e.isDisplayed() && !e.getText().trim().isEmpty()) return true;
        }
        return false;
    }

    // If you prefer passing driver, keep this too (optional)
    public AdminPlantFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForFormVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(headingEditPlant));
        wait.until(ExpectedConditions.visibilityOfElementLocated(plantNameInput));
    }

    public void setName(String name) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(plantNameInput));
        el.clear();
        el.sendKeys(name);
    }

    public String getName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(plantNameInput)).getAttribute("value");
    }

    public void selectCategoryByVisibleText(String category) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(categorySelect));
        new Select(el).selectByVisibleText(category);
    }

    public void setPrice(String price) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(priceInput));
        el.clear();
        el.sendKeys(price);
    }

    public String getPrice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(priceInput)).getAttribute("value");
    }

    public void setQuantity(String qty) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
        el.clear();
        el.sendKeys(qty);
    }

    // Backward-compatible method name if your steps use setStock(...)
    public void setStock(String qty) {
        setQuantity(qty);
    }

    public String getQuantity() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput)).getAttribute("value");
    }

    public void clickSave() {
        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
        save.click();
    }

    public void clickCancel() {
        WebElement cancel = wait.until(ExpectedConditions.elementToBeClickable(cancelLink));
        cancel.click();
    }

    public boolean isSaveButtonVisible() {
        return driver.findElements(saveBtn).size() > 0 && driver.findElement(saveBtn).isDisplayed();
    }

    public boolean isCancelVisible() {
        return driver.findElements(cancelLink).size() > 0 && driver.findElement(cancelLink).isDisplayed();
    }
}

