package models;

import java.util.Objects;

public class ClassifiedNews  extends News{
    private int id;
    private int employee_id;
    private int department_id;

    public ClassifiedNews(String title, String details) {
        super(title, details);
        this.employee_id = employee_id;
        this.department_id = department_id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClassifiedNews that = (ClassifiedNews) o;
        return getId() == that.getId() &&
                getEmployee_id() == that.getEmployee_id() &&
                getDepartment_id() == that.getDepartment_id();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getEmployee_id(), getDepartment_id());
    }
}
