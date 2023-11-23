package selenium.task40;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.helpers.BaseTest;

public class DownloadProgressTest extends BaseTest {

    @Test
    public void testDownloadProgress() {
        driver.get("https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html");

        WebElement downloadButton = driver.findElement(By.id("cricle-btn"));
        downloadButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class='percenttext']"), "50%"));

        driver.navigate().refresh();
    }
}
