package dao;

import models.ClassifiedNews;
import models.Department;
import models.News;

import java.util.List;

public interface DepartmentDao {

    void add(Department department);

    void addDepartmentToClassifiedNews(Department department, ClassifiedNews classifiedNews);
    void addClassifiedNewsToDepartment(ClassifiedNews classifiedNews , Department department);
    List<Department> getDepartments();
    List<ClassifiedNews> getAllDepartmentsForAClassified(int classifiedNews_id);

    void deleteById(int id);
    void clearAll();
}
