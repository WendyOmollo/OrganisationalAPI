package dao;

import models.ClassifiedNews;
import models.Department;
import models.Employee;

import java.util.List;

public interface EmployeeDao {

    void add(Employee employee);

    List<Employee> getEmployees();
    List<Employee> getAllEmployeesFromADepartment(int departmentId);

    void deleteById(int id);
    void clearAll();

}
