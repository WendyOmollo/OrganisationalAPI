package dao;

import models.Department;
import models.Employee;

import java.util.List;

public interface EmployeeDao {

    void add(Employee employee);
    void addEmployeeToDepartment(Employee employee , Department department);

    List<Employee> getEmployees();

    void deleteById(int id);
    void clearAll();

}
