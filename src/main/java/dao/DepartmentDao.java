package dao;

import models.Department;
import models.News;

import java.util.List;

public interface DepartmentDao {

    void add(Department department);
    void addDepartmentToNews(News news , Department department);

    List<Department> getDepartments();

    void deleteById(int id);
    void clearAll();
}
