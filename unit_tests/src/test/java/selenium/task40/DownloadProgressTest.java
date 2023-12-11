package selenium.task40;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.helpers.BaseTest;

public class DownloadProgressTest extends BaseTest {

    @Test
    public void testDownloadProgress() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html");

            DownloadProgressPage downloadProgressPage = new DownloadProgressPage(driver);
            downloadProgressPage.clickDownloadButton();

            downloadProgressPage.waitForDownloadProgressToReach("50%");
            String progressText = downloadProgressPage.getProgressText();

            Assertions.assertEquals("50%", progressText, "Download progress did not reach 50% as expected.");
        } finally {
            driver.quit();
        }
    }
}
