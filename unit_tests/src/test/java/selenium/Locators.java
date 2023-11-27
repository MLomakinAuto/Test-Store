package selenium;

import org.openqa.selenium.By;

public class Locators {
    public static final By EMAIL_INPUT = By.id("identifierId");
    public static final By NEXT_BUTTON = By.id("identifierNext");
    public static final By PASSWORD_INPUT = By.xpath("//input[@type='password']");
    public static final By PASSWORD_NEXT_BUTTON = By.id("passwordNext");
    public static final By VALIDATION_ERROR = By.xpath("//div[@class='OyEIQ uSvLId' and @jsname='h9d3hd']");
    public static final By AE_EMAIL_INPUT = By.xpath("//input[@type='email']");
    public static final By AE_PW_INPUT = By.xpath("//input[@type='password']");
    public static final By AE_LOGIN_BUTTON = By.xpath("//button[@type='submit']");
    public static final By AE_LOGGED_IN = By.xpath("//i[contains(@class, 'fa fa-user')]");
    public static final By MULTI_SELECT = By.id("multi-select");
    public static final By ALERT_BOX = By.xpath("//button[@class='btn btn-default']");
    public static final By ALERT_CONFIRM_BOX = By.xpath("//button[@class='btn btn-default btn-lg']");
    public static final By ALERT_PROMPT = By.xpath("//button[contains(text(),'Click for Prompt Box')]");
    public static final By CONFIRM_DEMO = By.id("confirm-demo");
    public static final By PROMPT_DEMO = By.id("prompt-demo");
}