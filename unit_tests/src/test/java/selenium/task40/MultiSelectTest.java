package selenium.task40;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static selenium.Locators.MULTI_SELECT;

public class MultiSelectTest {
    @Test
    public void multiSelect() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kolka\\Downloads\\New folder (2)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.seleniumeasy.com/basic-select-dropdown-demo.html");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Select multiSelectDropdown = new Select(driver.findElement(MULTI_SELECT));

        List<WebElement> options = multiSelectDropdown.getOptions();
        int totalOptions = options.size();
        List<Integer> selectedIndices = new ArrayList<>();

        Random random = new Random();
        while (selectedIndices.size() < 3) {
            int randomIndex = random.nextInt(totalOptions);
            if (!selectedIndices.contains(randomIndex)) {
                selectedIndices.add(randomIndex);
                multiSelectDropdown.selectByIndex(randomIndex);
            }
        }
        List<String> expectedSelections = new ArrayList<>();
        for (int index : selectedIndices) {
            expectedSelections.add(options.get(index).getText());
        }

        List<WebElement> selectedOptions = multiSelectDropdown.getAllSelectedOptions();
        List<String> actualSelections = new ArrayList<>();
        for (WebElement selectedOption : selectedOptions) {
            actualSelections.add(selectedOption.getText());
        }

        boolean areSelectionsCorrect = actualSelections.containsAll(expectedSelections) &&
                expectedSelections.containsAll(actualSelections);

        if (areSelectionsCorrect) {
            System.out.println("Selected options are correct: " + actualSelections);
        } else {
            System.out.println("Selected options are not correct. Expected: " + expectedSelections + ", Actual: " + actualSelections);
        }

        driver.quit();
    }
}
