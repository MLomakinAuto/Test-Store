package selenium.task40;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DownloadProgressTest {

    private WebDriver driver;

    @BeforeAll
    public static void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kolka\\Downloads\\New folder (2)\\chromedriver.exe");
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testDownloadProgress() {
        driver.get("https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html");

        WebElement downloadButton = driver.findElement(By.id("cricle-btn"));
        downloadButton.click();

        while (true) {
            WebElement progressBar = driver.findElement(By.xpath("//div[@class='percenttext']"));
            String progressText = progressBar.getText();
            if (progressText.contains("50%")) {
                break;
            }
        }
        driver.navigate().refresh();

    }
}
