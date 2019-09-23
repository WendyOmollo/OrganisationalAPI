package models;

import java.util.Objects;

public class Employee {
    private String name;

    private String position;
    private int id;


    public Employee(String name, int department_id, String position) {
        this.name = name;

        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return
                getId() == employee.getId() &&
                Objects.equals(getName(), employee.getName()) &&
                Objects.equals(getPosition(), employee.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(),  getPosition(), getId());
    }
}
