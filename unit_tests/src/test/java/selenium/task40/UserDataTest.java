package selenium.task40;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class UserDataTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kolka\\Downloads\\New folder (2)\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testUserDataVisibility() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://demo.seleniumeasy.com/dynamic-data-loading-demo.html");
        WebElement getNewUserButton = driver.findElement(By.id("save"));
        getNewUserButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(), ' loading...')]")));

        WebElement dataElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='loading']//img")));

        WebElement nameAndLastNameElement = driver.findElement(By.xpath("//div[@id='loading']"));

        String nameAndLastNameText = nameAndLastNameElement.getText();

        String[] nameAndLastNameArray = nameAndLastNameText.split("\n");

        if (nameAndLastNameArray.length >= 2) {
            String actualName = nameAndLastNameArray[0].replace("First Name : ", "");
            String actualLastName = nameAndLastNameArray[2].replace("Last Name : ", "");
            System.out.println("Actual Name: " + actualName);
            System.out.println("Actual Last Name: " + actualLastName);
        } else {
            System.out.println("Error: Could not extract both first name and last name.");
        }

        Assertions.assertNotNull(dataElement);
        Assertions.assertTrue(nameAndLastNameElement.isDisplayed());
    }


}
