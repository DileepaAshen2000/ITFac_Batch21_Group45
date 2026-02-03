package admin.ui.pages;

import admin.ui.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddCategoryPage {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    private By categoryNameInput = By.id("name");
    private By parentDropdown = By.id("parentId");
    private By saveButton = By.xpath("//button[@type='submit']");

    private By nameValidationError =
            By.xpath("//*[contains(text(),'Category name must be between')]");

    public boolean isAddCategoryPageDisplayed() {
        return driver.getCurrentUrl().contains("/ui/categories/add");
    }

    public void enterCategoryName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryNameInput))
                .clear();
        driver.findElement(categoryNameInput).sendKeys(name);
    }

    public void selectParentCategory(String parentName) {
        Select select = new Select(
                wait.until(ExpectedConditions.visibilityOfElementLocated(parentDropdown))
        );
        select.selectByVisibleText(parentName);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton))
                .click();
    }

    public boolean isValidationErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nameValidationError))
                .isDisplayed();
    }

    public String getValidationErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nameValidationError))
                .getText();
    }
}
