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
        String sql = "INSERT INTO employees(name,position) VALUES(:name,:position)";
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
        String sql = "INSERT INTO departmentId_employeeId(department_id,employee_id) VALUES(:department_id,:employee_id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("department_id", department.getId())
                    .addParameter("employee_id", employee.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public void addClassifiedNewsToEmployee(Employee employee, ClassifiedNews classifiedNews) {
        String sql = "INSERT INTO employeeId_classifiedId(employee_id,classifiedNews_id) VALUES(:employee_id,:classifiedNews_id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("employee_id", employee.getId())
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
    public List<ClassifiedNews> getAllClassifiedsForAnEmployee(int employeeId) {
        ArrayList<ClassifiedNews> classifiedNews = new ArrayList<>();
        String joinQuery ="SELECT classifiedNews_id FROM employeeId_classifiedId WHERE employee_id = :employee_id";
        try(Connection con = sql2o.open()){
            List<Integer> allClassifiedsIds = con.createQuery(joinQuery)
                    .addParameter("id",employeeId)
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
    public List<Employee> getAllEmployeesFromADepartment(int departmentId) {
        ArrayList<Employee> employees = new ArrayList<>();
        String joinQuery = "SELECT employeeId FROM departmentId_employeeId where department_id =:department_id ";
        try(Connection con = sql2o.open()){
            List<Integer> allEmployeesIds = con.createQuery(joinQuery)
                    .addParameter("departmentId",departmentId)
                    .executeAndFetch(Integer.class);
            for(Integer employeeId:allEmployeesIds){
                String employeeQuery = "SELECT FROM employees WHERE id:employeeId";
                employees.add(con.createQuery(employeeQuery)
                        .addParameter("employeeId",employeeId)
                        .executeAndFetchFirst(Employee.class));
            }

        }catch(Sql2oException ex){
            System.out.println(ex);
        }
        return employees;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from employees WHERE id=:id";
        String deleteJoin = "DELETE from employeeId_classifiedId WHERE classifiedNews_id = :classifiedNews_id";
        String deleteJoinEmployee = "DELETE from departmentId_employeeId WHERE department_id = :department_id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("classifiedNews_id",id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("department_id",id)
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
