package admin.ui.pages;

import common.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryListPage {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    private By categoryMenuLink = By.xpath("//a[contains(@href, '/ui/categories') or contains(text(), 'Categories') or contains(text(), 'Category')]");

    private By addCategoryBtn = By.cssSelector("a[href*='/ui/categories/add']");

    private By categoryTable = By.cssSelector("table");

    public void navigateToCategoryList() {
        try {

            WebElement menuLink = wait.until(
                    ExpectedConditions.presenceOfElementLocated(categoryMenuLink)
            );

            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", menuLink);

            Thread.sleep(300);

            wait.until(ExpectedConditions.elementToBeClickable(categoryMenuLink)).click();

        } catch (Exception e) {
            System.out.println("Menu link not found, navigating directly to URL");
            driver.get("http://localhost:8008/ui/categories");
        }

        wait.until(ExpectedConditions.urlContains("/ui/categories"));
    }


    public void clickAddCategory() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addCategoryBtn));
        wait.until(ExpectedConditions.elementToBeClickable(addCategoryBtn)).click();

        wait.until(ExpectedConditions.urlContains("/ui/categories/add"));
    }


    public boolean isCategoryTableDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTable))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}