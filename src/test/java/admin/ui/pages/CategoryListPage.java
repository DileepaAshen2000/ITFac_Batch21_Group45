package admin.ui.pages;

import admin.ui.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryListPage {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // REAL locator from inspect
    private By addCategoryBtn =
            By.xpath("//a[contains(text(),'Add A Category')]");

    private By categoryTable = By.tagName("table");

    public void navigateToCategoryList() {
        driver.get("http://localhost:8008/ui/categories");
    }

    public void clickAddCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(addCategoryBtn))
                .click();
    }

    public boolean isCategoryTableDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTable))
                .isDisplayed();
    }
}
