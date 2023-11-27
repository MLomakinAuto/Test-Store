package selenium.task40;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import selenium.helpers.BaseTest;

import static selenium.Locators.*;

public class JavaAlertTests extends BaseTest {

    @Test
    public void alertBox() {
        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");
        driver.findElement(ALERT_BOX).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        try {
            driver.switchTo().alert();
            Assertions.fail("Alert is not closed");
        } catch (NoAlertPresentException e) {
        }
    }


    @Test
    public void alertConfirmBoxAccept() {
        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");
        driver.findElement(ALERT_CONFIRM_BOX).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        WebElement resultElement = driver.findElement(CONFIRM_DEMO);
        String resultText = resultElement.getText();

        Assertions.assertTrue(resultText.contains("You pressed OK!"), "Test failed: Expected text 'You pressed OK!' not found in the result.");
    }

    @Test
    public void alertConfirmBoxCancel() {
        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");
        driver.findElement(ALERT_CONFIRM_BOX).click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        WebElement resultElement = driver.findElement(CONFIRM_DEMO);
        String resultText = resultElement.getText();
        assert resultText.contains("You pressed Cancel!") : "Test failed: Expected text 'You pressed Cancel!' not found in the result.";
    }

    @Test
    public void promptBox() {
        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");
        driver.findElement(ALERT_PROMPT).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("test");
        alert.accept();

        WebElement resultElement = driver.findElement(PROMPT_DEMO);
        String resultText = resultElement.getText();
        assert resultText.contains("You have entered 'test' !") : "Test failed: Expected text 'You have entered 'test' !' not found in the result.";

    }
}
