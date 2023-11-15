package selenium.task40;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static selenium.Locators.*;

public class JavaAlertTests {
    WebDriver driver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kolka\\Downloads\\New folder (2)\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    public void alertBox() {
        driver.findElement(ALERT_BOX).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void alertConfirmBoxAccept(){

        driver.findElement(ALERT_CONFIRM_BOX).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        WebElement resultElement = driver.findElement(CONFIRM_DEMO);
        String resultText = resultElement.getText();
        assert resultText.contains("You pressed OK!") : "Test failed: Expected text 'You pressed OK!' not found in the result.";
    }
    @Test
    public void alertConfirmBoxCancel(){
        driver.findElement(ALERT_CONFIRM_BOX).click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        WebElement resultElement = driver.findElement(CONFIRM_DEMO);
        String resultText = resultElement.getText();
        assert resultText.contains("You pressed Cancel!") : "Test failed: Expected text 'You pressed Cancel!' not found in the result.";
    }

    @Test
    public void promptBox(){
        driver.findElement(ALERT_PROMPT).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("test");
        alert.accept();

        WebElement resultElement = driver.findElement(PROMPT_DEMO);
        String resultText = resultElement.getText();
        assert resultText.contains("You have entered 'test' !") : "Test failed: Expected text 'You have entered 'test' !' not found in the result.";

    }
}
