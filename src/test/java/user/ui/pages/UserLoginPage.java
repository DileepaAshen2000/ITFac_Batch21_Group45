package user.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserLoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameInput = By.name("username");
    private By passwordInput = By.name("password");
    private By loginButton = By.cssSelector("button[type='submit']");

    public UserLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openLoginPage() {
        driver.get("http://localhost:8008/ui/login");
    }

    public void loginAsUser(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput))
                .sendKeys(username);

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput))
                .sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();
    }
}
