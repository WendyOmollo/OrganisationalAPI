package models;

import java.util.Objects;

public class Employee {
    private String name;
    private int department_id;
    private String position;
    private int id;
    private int classifiedNews_id;

    public Employee(String name, int department_id, String position) {
        this.name = name;
        this.department_id = department_id;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getClassifiedNews_id() {
        return classifiedNews_id;
    }

    public void setClassifiedNews_id(int classifiedNews_id) {
        this.classifiedNews_id = classifiedNews_id;
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
        return getDepartment_id() == employee.getDepartment_id() &&
                getId() == employee.getId() &&
                getClassifiedNews_id() == employee.getClassifiedNews_id() &&
                Objects.equals(getName(), employee.getName()) &&
                Objects.equals(getPosition(), employee.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDepartment_id(), getPosition(), getId(), getClassifiedNews_id());
    }
}
