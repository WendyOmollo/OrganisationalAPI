package dao;

import models.Department;
import models.News;

import java.util.List;

public interface DepartmentDao {

    void add(Department department);
    void addDepartmentToNews( Department department,News news );

    List<Department> getDepartments();

    void deleteById(int id);
    void clearAll();
}
