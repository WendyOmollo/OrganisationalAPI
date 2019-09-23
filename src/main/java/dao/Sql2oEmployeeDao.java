package dao;

import models.ClassifiedNews;
import models.Department;
import models.Employee;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oEmployeeDao implements EmployeeDao {

    private final Sql2o sql2o;
    public Sql2oEmployeeDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(Employee employee) {
        String sql = "INSERT INTO employees(name,department_id,position,classifiedNews_id) VALUES(:name,:department_id,:position,:classifiedNews_id)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(employee)
                    .executeUpdate()
                    .getKey();
            employee.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void addEmployeeToDepartment(Employee employee, Department department) {
        String sql = "INSERT INTO employees(name,department_id,position,classifiedNews_id) VALUES(:name,:department_id,:position,:classifiedNews_id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", employee.getId())
                    .addParameter("department_id", department.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public void addClassifiedNewsToEmployee(Employee employee, ClassifiedNews classifiedNews) {
        String sql = "INSERT INTO employees(name,department_id,position,classifiedNews_id) VALUES(:name,:department_id,:position,:classifiedNews_id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", employee.getId())
                    .addParameter("classifiedNews_id", classifiedNews.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }


    }

    @Override
    public List<Employee> getEmployees() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM employees")
                    .executeAndFetch(Employee.class);
        }
    }

    @Override
    public List<ClassifiedNews> getAllClassifiedsForAnEmployee(int id) {
        ArrayList<ClassifiedNews> classifiedNews = new ArrayList<>();
        String joinQuery ="SELECT classifiedNews_id FROM employees WHERE id = :id";
        try(Connection con = sql2o.open()){
            List<Integer> allClassifiedsIds = con.createQuery(joinQuery)
                    .addParameter("id",id)
                    .executeAndFetch(Integer.class);
            for(Integer classifiedId :allClassifiedsIds){
                String classifiedQuery = "SELECT FROM classified_news WHERE id=:classifiedNews_id";
                classifiedNews.add(con.createQuery(classifiedQuery)
                .addParameter("classifiedNews_id",classifiedId)
                .executeAndFetchFirst(ClassifiedNews.class));
            }

        }catch(Sql2oException ex){
            System.out.println(ex);
        }
        return classifiedNews;
    }

    @Override
    public List<Department> getAllEmployeesFromADepartment(int id) {
        ArrayList<Department> departments = new ArrayList<>();
        String joinQuery = "SELECT department_id FROM employees where id=:id";
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
    public void deleteById(int id) {
        String sql = "DELETE from employees WHERE id=:id";
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
        String sql = "DELETE from employees";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
}
