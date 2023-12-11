package selenium.task60;


import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.helpers.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginLogout() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://magento.softwaretestingboard.com/");
        LoginPage loginPage = new LoginPage(driver);
        LogoutPage logoutPage = new LogoutPage(driver);
        loginPage.login("mykolalomakin854@gmail.com", "Qwerty12345");

        String expectedWelcomeMessage = "Welcome, qqqq qqqq!";
        WebElement welcomeMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".logged-in")));
        String actualWelcomeMessage = welcomeMessageElement.getText();
        Assertions.assertEquals(expectedWelcomeMessage, actualWelcomeMessage, "Login was not successful");

        logoutPage.logout();

        String pageSource = driver.getPageSource();
        boolean isWelcomeMessagePresent = pageSource.contains(expectedWelcomeMessage);
        Assertions.assertFalse(isWelcomeMessagePresent, "Welcome message is not present on the page.");
    }

}
