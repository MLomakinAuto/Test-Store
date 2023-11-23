package selenium.task40;

import org.junit.jupiter.api.Assertions;
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

        WebDriverWait wait = new WebDriverWait(driver, 60, 2);
        WebElement progressBar = driver.findElement(By.xpath("//div[@class='percenttext']"));
        wait.until(ExpectedConditions.textToBePresentInElement(progressBar, "50%"));
        String progressText = progressBar.getText();
        Assertions.assertEquals("50%", progressText, "Download progress did not reach 50% as expected.");
        driver.navigate().refresh();
    }
}
