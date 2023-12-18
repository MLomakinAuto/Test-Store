package selenium.task40;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DownloadProgressPage {
    private final WebDriver driver;

    private By downloadButtonLocator = By.id("cricle-btn");
    private By progressBarLocator = By.xpath("//div[@class='percenttext']");

    public DownloadProgressPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickDownloadButton() {
        driver.findElement(downloadButtonLocator).click();
    }

    public void waitForDownloadProgressToReach(String expectedProgress) {
        WebDriverWait wait = new WebDriverWait(driver, 60, 2);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(progressBarLocator, expectedProgress));
    }

    public String getProgressText() {
        return driver.findElement(progressBarLocator).getText();
    }
}
