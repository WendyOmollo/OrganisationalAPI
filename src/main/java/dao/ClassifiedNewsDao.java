package dao;

import models.ClassifiedNews;
import models.Department;
import models.Employee;

import java.util.List;

public interface ClassifiedNewsDao {

    void add(ClassifiedNews classifiedNews);

    void addClassifiedNewsToDepartment(ClassifiedNews classifiedNews , Department department);
    void addClassifiedNewsToEmployee(ClassifiedNews classifiedNews, Employee employee);

    List<ClassifiedNews> getClassifiedNews();
    List<Department> getAllClassifiedNewsByDepartment(int id);
    List<Employee> getAllClassifiedNewsByEmployee(int id);

    void deleteById(int id);
    void clearAll();
}
