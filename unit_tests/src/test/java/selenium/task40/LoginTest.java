package selenium.task40;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static selenium.Locators.*;

public class LoginTest {
    WebDriver driver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kolka\\Downloads\\New folder (2)\\chromedriver.exe");

        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @CsvSource({"mykolalomakin854@gmail.com, 12345", "mykolalomakin854+1@gmail.com, 12345"})
    public void loginTest(String username, String password) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // implicit wait

        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.get("https://www.automationexercise.com/login");
        WebElement usernameField = driver.findElement(AE_EMAIL_INPUT);
        usernameField.sendKeys(username);
        WebElement passwordField = driver.findElement(AE_PW_INPUT);
        passwordField.sendKeys(password);
        WebElement loginButton = driver.findElement(AE_LOGIN_BUTTON);
        loginButton.click();

        wait.pollingEvery(1000, TimeUnit.MILLISECONDS);
        WebElement loggedInUser = wait.until(ExpectedConditions.visibilityOfElementLocated(AE_LOGGED_IN));

        assertTrue(loggedInUser.isDisplayed(), "Element is not displayed");
    }
}
