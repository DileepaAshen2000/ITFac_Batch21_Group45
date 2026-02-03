package admin.ui.pages;

import admin.ui.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openLoginPage() {
        driver.get("http://localhost:8008/ui/login");
    }

    public void loginAsAdmin(String username, String password) {
        WebElement usernameField =
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.name("username")));

        WebElement passwordField =
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.name("password")));

        WebElement loginButton =
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[type='submit']")));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}