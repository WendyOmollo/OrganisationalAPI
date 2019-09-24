import dao.*;
import models.ClassifiedNews;
import models.Department;
import models.Employee;
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

                String connectionString = "jdbc:h2:~/organisation.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
                Sql2o sql2o = new Sql2o(connectionString, "", "");

                employeeDao = new Sql2oEmployeeDao(sql2o);
                newsDao = new Sql2oNewsDao(sql2o);
                departmentDao = new Sql2oDepartmentDao(sql2o);
                classifiedNewsDao = new Sql2oClassifiedNewsDao(sql2o);
                conn = sql2o.open();

                get("/departments","application/json",(request, response) -> {
                    return gson.toJson(departmentDao.getDepartments());
                });

                get("/departments/:id","application/json",(request, response) -> {
                    int departmentId = Integer.parseInt(request.params("id"));
                    return gson.toJson(departmentDao.findById(departmentId));
                });
                get("/employees","application/json",(request, response) -> {
                    return gson.toJson(employeeDao.getEmployees());
                });

                get("/employees/:id","application/json",(request, response) -> {
                    response.type("application/json");
                    int employeeId = Integer.parseInt(request.params("id"));
                    return gson.toJson(departmentDao.findById(employeeId));
                });
                get("/classifiedNews","",(request, response) -> {
                    return gson.toJson(classifiedNewsDao.getClassifiedNews());
                });

                get("/classifiedNews/:id","application/json",(request, response) -> {
                    int classifiedId = Integer.parseInt(request.params("id"));
                    return gson.toJson(classifiedNewsDao.findById(classifiedId));
                });

                post("/departments/new","application/json",(request, response) -> {
                    Department department = gson.fromJson(request.body(),Department.class);
                    departmentDao.add(department);
                    response.status(201);
                    return gson.toJson(department);
                });
                post("/departments/:department_id/employees/new", "application/json", (req, res) -> {
                    int departmentId = Integer.parseInt(req.params("department_id"));
                    Department department = gson.fromJson(req.body(),Department.class);
                    Employee employee = gson.fromJson(req.body(), Employee.class);
                    employee.setDepartment_id(departmentId);
                    System.out.println(departmentId);
                    employeeDao.add(employee);
                    res.status(201);
                    return gson.toJson(employee);
                });
                post("/departments/:department_id/classifiedNews/:classifiedNews_id","application/json",(request, response) -> {
                    int departmentId = Integer.parseInt(request.params("department_id"));
                    int classifiedId = Integer.parseInt(request.params("id"));
                    Department department = departmentDao.findById(departmentId);
                    ClassifiedNews classifiedNews = classifiedNewsDao.findById(classifiedId);

                    if(department!=null && classifiedNews !=null){
                        classifiedNewsDao.addClassifiedNewsToDepartment(classifiedNews,department);
                        response.status(201);
                        return gson.toJson(String.format("Department '%s' and ClassifiedNews '%s' have been associated",classifiedNews.getTitle(),department.getName()));
                    }else{
                        throw new ApiException(404,String.format("Department or ClassifiedNews does not exist"));
                    }

                });

                after((req, res) ->{
                    res.type("application/json");
                });

            }
        }
