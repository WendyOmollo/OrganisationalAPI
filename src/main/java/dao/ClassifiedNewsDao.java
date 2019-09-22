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

    void deleteById(int id);
    void clearAll();
}
