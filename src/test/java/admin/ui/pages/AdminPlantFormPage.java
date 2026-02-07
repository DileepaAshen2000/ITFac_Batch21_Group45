
package admin.ui.pages;

import common.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminPlantFormPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By headingEditPlant = By.xpath("//h3[normalize-space()='Edit Plant' or normalize-space()='Add Plant']");
    private final By saveBtn = By.cssSelector("form button.btn.btn-primary");
    private final By cancelLink = By.cssSelector("form a.btn.btn-secondary[href='/ui/plants']");

    private final By plantNameInput = By.xpath("//label[normalize-space()='Plant Name']/following::input[1]");
    private final By categorySelect = By.xpath("//label[normalize-space()='Category']/following::select[1]");
    private final By priceInput = By.xpath("//label[normalize-space()='Price']/following::input[1]");
    private final By quantityInput = By.xpath("//label[normalize-space()='Quantity']/following::input[1]");

    public AdminPlantFormPage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


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

