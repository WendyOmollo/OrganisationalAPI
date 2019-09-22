package dao;

import models.ClassifiedNews;
import models.Department;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao {

    private final Sql2o sql2o;
    public Sql2oDepartmentDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(Department department) {
    String sql = "INSERT INTO departments(name,description,employees,classifiedNews_id) VALUES(:name,:description,:employees,:classifiedNews_id)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addDepartmentToClassifiedNews(Department department, ClassifiedNews classifiedNews) {
        String sql = "INSERT INTO departments(name,description,employees,classifiedNews_id) VALUES(:name,:description,:employees,:classifiedNews_id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", department.getId())
                    .addParameter("classifiedNews_id", classifiedNews.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void addClassifiedNewsToDepartment(ClassifiedNews classifiedNews, Department department) {
        String sql = "INSERT INTO departments(name,description,employees,classifiedNews_id) VALUES(:name,:description,:employees,:classifiedNews_id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", department.getId())
                    .addParameter("classifiedNews_id", classifiedNews.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }


    @Override
    public List<Department> getDepartments() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Department.class);
        }
    }

    @Override
    public List<ClassifiedNews> getAllDepartmentsForAClassified(int classifiedNews_id) {
        List<ClassifiedNews> classifiedNews = new ArrayList<>();
        return classifiedNews;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from departments WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE from departments";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
}
