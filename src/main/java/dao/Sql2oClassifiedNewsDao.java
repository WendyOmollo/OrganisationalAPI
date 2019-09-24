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
    String sql = "INSERT INTO classified_news(title,details) VALUES(:title,:details)";
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
    public List<ClassifiedNews> getClassifiedNews() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM classified_news")
                    .executeAndFetch(ClassifiedNews.class);
        }
    }

    @Override
    public void addClassifiedNewsToDepartment(ClassifiedNews classifiedNews, Department department) {
        String sql = "INSERT INTO departments_classifiedNews(department_id,classifiedNews_id) VALUES(:departmentId,:classifiedNewsId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("departmentId", department.getId())
                    .addParameter("classifiedNewsId", classifiedNews.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Department> getAllDepartmentsForAClassifiedNews(int classifiedNewsId) {
        ArrayList<Department> departments = new ArrayList<>();
        String joinQuery ="SELECT department_id FROM departments_classifiedNews WHERE classifiedNews_id = :classifiedNewsId";
        try(Connection con = sql2o.open()){
            List<Integer> allDepartmentsIds = con.createQuery(joinQuery)
                    .addParameter("classifiedNewsId",classifiedNewsId)
                    .executeAndFetch(Integer.class);
            for(Integer departmentId :allDepartmentsIds){
                String departmentQuery = "SELECT * FROM departments WHERE id = :departmentId";
                departments.add(con.createQuery(departmentQuery)
                        .addParameter("departmentId",departmentId)
                        .executeAndFetchFirst(Department.class));
            }
        }catch(Sql2oException ex){
            System.out.println(ex);
        }
        return departments;
    }

    @Override
    public ClassifiedNews findById(int id) {
        String sql = "SELECT * FROM classified_news WHERE id =:id";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(ClassifiedNews.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from classified_news WHERE id = :id";
        String deleteJoin = "DELETE from departmentId_classifiedId WHERE department_id = :department_id";
        String deleteJoinClassified = "DELETE from employeeId_classifiedId WHERE employee_id = :employee_id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
//            con.createQuery(deleteJoin)
//                    .addParameter("department_id", id)
//                    .executeUpdate();
//            con.createQuery(deleteJoin)
//                    .addParameter("employee_id", id)
//                    .executeUpdate();
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
