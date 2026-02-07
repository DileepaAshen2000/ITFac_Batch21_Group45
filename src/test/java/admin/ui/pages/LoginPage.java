package admin.ui.pages;

import common.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton   = By.xpath("//button[@type='submit']");

    public void login(String username, String password) {
        driver.get("http://localhost:8008/ui/login");

        WebElement user =
                wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        user.clear();
        user.sendKeys(username);

        WebElement pass =
                wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        pass.clear();
        pass.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

        wait.until(ExpectedConditions.urlContains("/categories"));
    }
}
