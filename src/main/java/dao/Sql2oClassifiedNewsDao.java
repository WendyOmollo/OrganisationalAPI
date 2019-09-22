package dao;

import models.ClassifiedNews;
import models.Department;
import models.Employee;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oClassifiedNewsDao implements ClassifiedNewsDao {

    private final Sql2o sql2o;
    public Sql2oClassifiedNewsDao(Sql2o sql2o) { this.sql2o = sql2o; }
    @Override
    public void add(ClassifiedNews classifiedNews) {
    String sql = "INSERT INTO classified_news(title,details,department_id,employee_id) VALUES(:title,:details,:department_id,:employee_id)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(classifiedNews)
                    .executeUpdate()
                    .getKey();
            classifiedNews.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addClassifiedNewsToDepartment(ClassifiedNews classifiedNews, Department department) {
        String sql = "INSERT INTO classified_news(title,details,department_id,employee_id) VALUES(:title,:details,:department_id,:employee_id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("department_id", department.getId())
                    .addParameter("id", classifiedNews.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public void addClassifiedNewsToEmployee(ClassifiedNews classifiedNews, Employee employee) {
        String sql = "INSERT INTO classified_news(title,details,department_id,employee_id) VALUES(:title,:details,:department_id,:employee_id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("employee_id", employee.getId())
                    .addParameter("id", classifiedNews.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public List<ClassifiedNews> getClassifiedNews() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM classified_news")
                    .executeAndFetch(ClassifiedNews.class);
        }
    }

    @Override
    public List<Department> getAllClassifiedNewsNyDepartment(int department_id) {
        List<Department> departments = new ArrayList<>();
        return departments;
    }

    @Override
    public List<Employee> getAllClassifiedNewsByEmployee(int employee_id) {
        List<Employee> employees = new ArrayList<>();
        return employees;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from classified_news WHERE id=:id";
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
        String sql = "DELETE from classified_news";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
}
