package dao;

import models.ClassifiedNews;
import models.Department;
import models.Employee;

import java.util.List;

public interface ClassifiedNewsDao {

    void add(ClassifiedNews classifiedNews);

    void addClassifiedNewsToDepartment(ClassifiedNews classifiedNews , Department department);
    void addEmployeeToClassifiedNews(ClassifiedNews classifiedNews, Employee employee);

    List<ClassifiedNews> getClassifiedNews();
    List<Department> getAllClassifiedNewsNyDepartment(int department_id);
    List<Employee> getAllClassifiedNewsByEmployee(int employee_id);

    void deleteById(int id);
    void clearAll();
}
