package selenium.task60;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage{
    private WebDriver driver;

    @FindBy(className = "customer-name")
    private WebElement extendActions;
    @FindBy(linkText = "Sign Out")
    private WebElement logoutButton;


    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        extendActions.click();
        logoutButton.click();
    }
}
