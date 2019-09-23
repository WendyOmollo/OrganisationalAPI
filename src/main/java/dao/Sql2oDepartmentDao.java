package dao;

import models.ClassifiedNews;
import models.Department;
import models.Employee;
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
    String sql = "INSERT INTO departments(name,description,employees) VALUES(:name,:description,:employees)";
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
        String sql = "INSERT INTO departmentId_classifiedId(department_id,classifiedNews_id) VALUES(:department_id:classifiedNews_id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("department_id", department.getId())
                    .addParameter("classifiedNews_id", classifiedNews.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void addClassifiedNewsToDepartment(ClassifiedNews classifiedNews, Department department) {
        String sql = "INSERT INTO departmentId_classifiedId(department_id,classifiedNews_id) VALUES(:department_id:classifiedNews_id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("department_id", department.getId())
                    .addParameter("classifiedNews_id", classifiedNews.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void addEmployeeToDepartment(Department department, Employee employee) {
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
    public List<Department> getDepartments() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Department.class);
        }
    }


    @Override
    public List<ClassifiedNews> getAllClassifiedNewsByDepartment(int departmentId) {
        ArrayList<ClassifiedNews> classifiedNews = new ArrayList<>();
        String joinQuery ="SELECT classifiedNews_id FROM departmentId_classifiedId WHERE department_id = :department_id";
        try(Connection con = sql2o.open()){
            List<Integer> allClassifiedsIds = con.createQuery(joinQuery)
                    .addParameter("departmentId",departmentId)
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
        String joinQuery = "SELECT employeeId FROM departmentId_employeeId where department_id =:department_id";
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
    public Department findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM departments WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Department.class);
        }
    }


    @Override
    public void deleteById(int id) {
        String sql = "DELETE from departments WHERE id=:id";
        String deleteJoin = "DELETE from departments WHERE classifiedNews_id = :classifiedNews_id";
        String deleteJoinEmployee = "DELETE from departments WHERE employee_id = :employee_id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("classifiedNews_id",id)
                    .executeUpdate();
            con.createQuery(deleteJoinEmployee)
                    .addParameter("employee_id",id)
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
