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
    public List<Department> getAllClassifiedNewsByDepartment(int id) {
        ArrayList<Department> departments = new ArrayList<>();
        String joinQuery = "SELECT department_id FROM classified_news where id=:id";
        try(Connection con = sql2o.open()){
            List<Integer> allDepartmentsIds = con.createQuery(joinQuery)
                    .addParameter("id",id)
                    .executeAndFetch(Integer.class);
            for(Integer departmentId : allDepartmentsIds){
                String departmentQuery = "SELECT FROM departments WHERE id=:department_id";
                departments.add(con.createQuery(departmentQuery)
                            .addParameter("department_id",departmentId)
                        .executeAndFetchFirst(Department.class));
            }
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
        return departments;
    }

    @Override
    public List<Employee> getAllClassifiedNewsByEmployee(int id) {
        ArrayList<Employee> employees = new ArrayList<>();
        String joinQuery = "SELECT employee_id FROM classified_news where id =:id ";
            try(Connection con = sql2o.open()){
                List<Integer> allEmployeesIds = con.createQuery(joinQuery)
                        .addParameter("id",id)
                        .executeAndFetch(Integer.class);
                for(Integer employeeId:allEmployeesIds){
                    String employeeQuery = "SELECT FROM employees WHERE id:employee_id";
                    employees.add(con.createQuery(employeeQuery)
                            .addParameter("employee_id",employeeId)
                            .executeAndFetchFirst(Employee.class));
                }

            }catch(Sql2oException ex){
                System.out.println(ex);
            }
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
