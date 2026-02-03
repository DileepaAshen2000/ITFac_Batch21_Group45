package user.ui.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
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

    public UserCategoryListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickIdHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(idHeader))
                .click();
    }

    public List<Integer> getCategoryIds() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(idColumnCells));

        List<Integer> ids = new ArrayList<>();
        List<WebElement> cells = driver.findElements(idColumnCells);

        for (WebElement cell : cells) {
            ids.add(Integer.parseInt(cell.getText().trim()));
        }
        return ids;
    }
}
