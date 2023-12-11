package selenium.task60;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "pass")
    private WebElement passwordField;

    @FindBy(className = "authorization-link")
    private WebElement loginButton;

    @FindBy(id = "send2")
    private WebElement signUp;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        loginButton.click();
        emailField.sendKeys(username);
        passwordField.sendKeys(password);
        signUp.click();
    }
}