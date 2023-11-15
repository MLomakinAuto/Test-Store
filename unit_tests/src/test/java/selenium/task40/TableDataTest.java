package selenium.task40;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TableDataTest {
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
    public void testFilterTableData() {
        driver.get("https://demo.seleniumeasy.com/table-sort-search-demo.html");

        WebElement dropdown = driver.findElement(By.name("example_length"));
        dropdown.click();
        WebElement option10 = driver.findElement(By.xpath("//*[@id='example_length']/label/select/option[1]"));
        option10.click();
        List<Map<String, String>> dataMapList = new ArrayList<>();
        int x = 30;
        double y = 95400;
        try {
            do {
                List<WebElement> rows = driver.findElements(By.xpath("//table[@id='example']/tbody/tr"));

                for (WebElement row : rows) {
                    List<WebElement> columns = row.findElements(By.tagName("td"));

                    String name = columns.get(0).getText();
                    String position = columns.get(1).getText();
                    String office = columns.get(2).getText();
                    int age = Integer.parseInt(columns.get(3).getText());

                    String salaryStr = columns.get(5).getText();
                    salaryStr = salaryStr.replaceAll("[^\\d.]", "");
                    double salary = Double.parseDouble(salaryStr);

                    if (age > x && salary <= y) {
                        Map<String, String> dataMap = new HashMap<>();
                        dataMap.put("Name", name);
                        dataMap.put("Position", position);
                        dataMap.put("Office", office);

                        dataMapList.add(dataMap);
                    }
                }

                WebElement nextButton = driver.findElement(By.id("example_next"));
                if (nextButton.getAttribute("class").contains("disabled")) {
                    break;
                } else {
                    nextButton.click();
                }
            } while (true);

            for (Map<String, String> dataMap : dataMapList) {
                System.out.println("Name: " + dataMap.get("Name") + ", Position: " + dataMap.get("Position") + ", Office: " + dataMap.get("Office"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
