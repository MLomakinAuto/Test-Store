package selenium.task40;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.helpers.BaseTest;
import selenium.helpers.Employee;
import java.util.ArrayList;
import java.util.List;



public class TableDataTest extends BaseTest {


    @Test
    public void testFilterTableData() {
        driver.get("https://demo.seleniumeasy.com/table-sort-search-demo.html");

        WebElement dropdown = driver.findElement(By.name("example_length"));
        dropdown.click();
        WebElement option10 = driver.findElement(By.xpath("//*[@id='example_length']/label/select/option[1]"));
        option10.click();
        List<Employee> employeeList = new ArrayList<>();

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
                        Employee employee = new Employee();
                        employee.setName(name);
                        employee.setPosition(position);
                        employee.setOffice(office);

                        employeeList.add(employee);
                    }
                }

                WebElement nextButton = driver.findElement(By.id("example_next"));
                if (nextButton.getAttribute("class").contains("disabled")) {
                    break;
                } else {
                    nextButton.click();
                }
            } while (true);

            List<Employee> expectedEmployeeList = new ArrayList<>();
            Employee employee1 = new Employee();
            employee1.setName("A. Cox");
            employee1.setPosition("Junior Technical Author");
            employee1.setOffice("San Francisco");
            expectedEmployeeList.add(employee1);

            Employee employee2 = new Employee();
            employee2.setName("G. Joyce");
            employee2.setPosition("Developer");
            employee2.setOffice("Edinburgh");
            expectedEmployeeList.add(employee2);

            Employee employee3 = new Employee();
            employee3.setName("M. House");
            employee3.setPosition("Integration Specialist");
            employee3.setOffice("Sidney");
            expectedEmployeeList.add(employee3);


            Assertions.assertEquals(expectedEmployeeList, employeeList, "Table data does not match expected results.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}