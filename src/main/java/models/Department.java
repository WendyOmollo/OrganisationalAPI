package models;

public class Department {

    private String name;
    private String description;
    private int employees;
    private int id;

    public Department(String name,String description,int employees){
        this.name=name;
        this.description = description;
        this.employees=employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }
}
