package selenium;

import org.openqa.selenium.By;

public class Locators {
    public static final By EMAIL_INPUT = By.id("identifierId");
    public static final By NEXT_BUTTON = By.id("identifierNext");
    public static final By PASSWORD_INPUT = By.xpath("//input[@type='password']");
    public static final By PASSWORD_NEXT_BUTTON = By.id("passwordNext");
    public static final By VALIDATION_ERROR = By.xpath("//div[@class='OyEIQ uSvLId' and @jsname='h9d3hd']");
}