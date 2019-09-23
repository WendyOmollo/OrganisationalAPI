package dao;

import models.ClassifiedNews;
import models.Department;
import models.Employee;
import models.News;

import java.util.List;

public interface DepartmentDao {

    void add(Department department);

    void addDepartmentToClassifiedNews(Department department, ClassifiedNews classifiedNews);
    void addClassifiedNewsToDepartment(ClassifiedNews classifiedNews , Department department);
    void addEmployeeToDepartment(Department department, Employee employee);

    List<Department> getDepartments();
    List<ClassifiedNews> getAllDepartmentsForAClassified(int classifiedNews_id);
    List<Employee> getAllEmployeesFromADepartment(int employee_id);

    void deleteById(int id);
    void clearAll();
}
