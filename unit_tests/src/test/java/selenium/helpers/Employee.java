package selenium.helpers;

import java.util.Objects;

public class Employee {
    private String name;
    private String position;
    private String office;


    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public void setOffice(String office) {
        this.office = office;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(position, employee.position) &&
                Objects.equals(office, employee.office);
    }


    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", office='" + office + '\'' +
                '}';
    }
}
