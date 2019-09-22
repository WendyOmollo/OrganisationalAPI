package dao;

import models.ClassifiedNews;
import models.Department;
import models.Employee;

import java.util.List;

public interface EmployeeDao {

    void add(Employee employee);

    void addEmployeeToDepartment(Employee employee , Department department);

    void addClassifiedNewsToEmployee(Employee employee, ClassifiedNews classifiedNews);

    List<Employee> getEmployees();
    List<ClassifiedNews> getAllEmployeesForAClassified(int classifiedNews_id);

    void deleteById(int id);
    void clearAll();

}
