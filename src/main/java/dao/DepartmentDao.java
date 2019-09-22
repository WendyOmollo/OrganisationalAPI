package dao;

import models.ClassifiedNews;
import models.Department;
import models.News;

import java.util.List;

public interface DepartmentDao {

    void add(Department department);

    void addDepartmentToClassifiedNews(Department department, ClassifiedNews classifiedNews);

    List<Department> getDepartments();

    void deleteById(int id);
    void clearAll();
}
