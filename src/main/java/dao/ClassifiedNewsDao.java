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
    List<ClassifiedNews> getAllClassifiedNewsByDepartment(int departmentId);
    List<ClassifiedNews> getAllClassifiedNewsByEmployee(int employeeId);

    void deleteById(int id);
    void clearAll();
}
