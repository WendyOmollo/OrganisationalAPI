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
    public void addClassifiedNewsToEmployee(ClassifiedNews classifiedNews, Employee employee) {
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
    public List<ClassifiedNews> getClassifiedNews() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM classified_news")
                    .executeAndFetch(ClassifiedNews.class);
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
    public List<ClassifiedNews> getAllClassifiedNewsByEmployee(int employeeId) {
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
    public void deleteById(int id) {
        String sql = "DELETE from classified_news WHERE id=:id";
        String deleteJoin = "DELETE from departmentId_classifiedId WHERE department_id = :department_id";
        String deleteJoinClassified = "DELETE from employeeId_classifiedId WHERE employee_id = :employee_id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("department_id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("employee_id", id)
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
