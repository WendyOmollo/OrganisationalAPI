package dao;

import models.ClassifiedNews;
import models.Department;
import models.Employee;

import java.util.List;

public interface DepartmentDao {

    void add(Department department);

    void addClassifiedNewsToDepartment(ClassifiedNews classifiedNews , Department department);

    void addEmployeeToDepartment(Department department, Employee employee);

    List<Department> getDepartments();

    List<ClassifiedNews> getAllClassifiedNewsByDepartment(int departmentId);

    List<Employee> getAllEmployeesFromADepartment(int employee_id);

    Department findById(int id);

    void deleteById(int id);
    void clearAll();
}
