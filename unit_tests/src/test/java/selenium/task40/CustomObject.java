package selenium.task40;

public class CustomObject {
    private String name;
    private String position;
    private String office;

    public CustomObject(String name, String position, String office) {
        this.name = name;
        this.position = position;
        this.office = office;
    }

    // Getters for the fields
    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getOffice() {
        return office;
    }
}
