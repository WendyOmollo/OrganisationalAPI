package dao;

import models.ClassifiedNews;
import models.Department;
import models.Employee;

import java.util.List;

public interface ClassifiedNewsDao {

    void add(ClassifiedNews classifiedNews);

    void addClassifiedNewsToDepartment(ClassifiedNews classifiedNews , Department department);

    List<ClassifiedNews> getClassifiedNews();

    List<Department> getAllDepartmentsForAClassifiedNews(int id);

    ClassifiedNews findById(int id);

    void deleteById(int id);

    void clearAll();
}
