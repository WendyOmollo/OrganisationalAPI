import dao.*;
import models.Department;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;
import com.google.gson.Gson;


public class App {
            public static void main(String[] args) {
                Sql2oEmployeeDao employeeDao;
                Sql2oNewsDao newsDao;
                Sql2oDepartmentDao departmentDao;
                Sql2oClassifiedNewsDao classifiedNewsDao;
                Connection conn;
                Gson gson = new Gson();

                String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
                Sql2o sql2o = new Sql2o(connectionString, "", "");

                employeeDao = new Sql2oEmployeeDao(sql2o);
                newsDao = new Sql2oNewsDao(sql2o);
                departmentDao = new Sql2oDepartmentDao(sql2o);
                classifiedNewsDao = new Sql2oClassifiedNewsDao(sql2o);
                conn = sql2o.open();

                get("/departments","application/json",(request, response) -> {
                    response.type("application/json");
                    return gson.toJson(departmentDao.getDepartments());
                });

                get("/departments/:id","application/json",(request, response) -> {
                    response.type("application/json");
                    int departmentId = Integer.parseInt(request.params("id"));
                    response.type("application/json");
                    return gson.toJson(departmentDao.findById(departmentId));
                });

                post("departments/new","application/json",(request, response) -> {
                    Department department = gson.fromJson(request.body(),Department.class);
                    departmentDao.add(department);
                    response.status(201);
                    response.type("application/json");
                    return gson.toJson(department);
                });
            }
        }
