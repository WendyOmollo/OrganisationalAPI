package dao;

import models.ClassifiedNews;
import models.Department;
import models.Employee;

import java.util.List;

public interface DepartmentDao {

    void add(Department department);

    void addClassifiedNewsToDepartment(ClassifiedNews classifiedNews , Department department);

    List<Department> getDepartments();

    List<ClassifiedNews> getAllClassifiedNewsByDepartment(int departmentId);

    Department findById(int id);

    void deleteById(int id);
    void clearAll();
}
