package selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static selenium.Locators.*;

public class GmailLoginTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kolka\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void testGmailLogin() {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get("https://accounts.google.com/signin");

        WebElement emailInput = driver.findElement(EMAIL_INPUT);
        emailInput.sendKeys("mykolalomakin854@gmail.com");


        WebElement nextButton = driver.findElement(NEXT_BUTTON);
        nextButton.click();

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT));
        passwordInput.sendKeys("TEST123456");

        WebElement passwordNextButton = driver.findElement(PASSWORD_NEXT_BUTTON);
        passwordNextButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(VALIDATION_ERROR));
        Assertions.assertTrue(errorMessage.isDisplayed(), "Error message not displayed: Wrong password");

    }
}
