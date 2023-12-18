package selenium.task60;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import selenium.helpers.BaseTest;

public class LogoutTest extends BaseTest {

    @Test
    public void logoutTest(){
        driver.get("https://magento.softwaretestingboard.com/");
        LoginPage loginPage = new LoginPage(driver);
        LogoutPage logoutPage = new LogoutPage(driver);
        loginPage.login("mykolalomakin854@gmail.com", "Qwerty12345");
        String expectedWelcomeMessage = "Welcome, qqqq qqqq!";
        logoutPage.logout();
        String pageSource = driver.getPageSource();
        boolean isWelcomeMessagePresent = pageSource.contains(expectedWelcomeMessage);
        Assertions.assertFalse(isWelcomeMessagePresent, "Welcome message is not present on the page.");
    }

}
