package selenium.task40;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.helpers.BaseTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static selenium.Locators.MULTI_SELECT;

public class MultiSelectTest extends BaseTest {
    @Test
    public void multiSelect() {
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

        Assertions.assertTrue(actualSelections.containsAll(expectedSelections) &&
                        expectedSelections.containsAll(actualSelections),
                "Selected options are not correct. Expected: " + expectedSelections + ", Actual: " + actualSelections);
    }
}
