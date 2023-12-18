package selenium.helpers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

public class BaseTest {
    protected WebDriver driver;
    protected ScreenshotUtils screenshotUtils;

    protected void captureScreenshot(String methodName) {
        if (screenshotUtils != null) {
            screenshotUtils.captureScreenshot(methodName);
        }
    }

    @BeforeEach
    void setUp() {
        String driverPath = "src/main/resources/chromedriver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        screenshotUtils = new ScreenshotUtils(driver);
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        screenshotUtils = new ScreenshotUtils(driver);
        if (driver != null) {
            captureScreenshot(Objects.requireNonNull(testInfo.getTestMethod().orElse(null)).getName());
            driver.quit();
        }
    }
}