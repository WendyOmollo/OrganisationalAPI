package dao;

import models.Department;
import models.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oEmployeeDaoTest {
    private Connection conn;
    private Sql2oClassifiedNewsDao classifiedNewsDao;
    private Sql2oDepartmentDao departmentDao;
    private Sql2oEmployeeDao employeeDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        classifiedNewsDao = new Sql2oClassifiedNewsDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        employeeDao = new Sql2oEmployeeDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    public Employee setUpEmployee(){
        Employee employee = new Employee("Michael","Managing Director");
        employeeDao.add(employee);
        return employee;
    }

    public Employee setUpAnotherEmployee(){
        Employee employee = new Employee("Joseph","Secretary");
        employeeDao.add(employee);
        return employee;
    }

    public Department setUpDepartment(){
        Department department = new Department("Hospitality","Found in love",24);
        departmentDao.add(department);
        return department;
    }
    public Department setUpAnotherDepartment(){
        Department department = new Department("Finance","Trust us with your future",30);
        departmentDao.add(department);
        return department;
    }
    public Employee setupEmployeeForDepartment(Department department) {
        Employee employee = new Employee("Moses", "Executive Director",department.getId());
        employeeDao.add(employee);
        return employee;
    }
    @Test
    public void addingEmployeeSetsId() throws Exception {
        Employee testEmployee = setUpEmployee();
        assertEquals(1, testEmployee.getId());
    }
    @Test
    public void getAll() throws Exception {
        Employee employee1 = setUpEmployee();
        Employee employee2 = setUpAnotherEmployee();
        assertEquals(2, employeeDao.getEmployees().size());
    }
    @Test
    public void getAllEmployeesByDepartment() throws Exception {
        Department testDepartment = setUpDepartment();
        Department otherDepartment = setUpDepartment(); //add in some extra data to see if it interferes
        Employee review1 = setupEmployeeForDepartment(testDepartment);
        Employee review2 = setupEmployeeForDepartment(testDepartment);
        Employee reviewForOtherDepartment = setupEmployeeForDepartment(otherDepartment);
        assertEquals(2, employeeDao.getAllEmployeesFromADepartment(testDepartment.getId()).size());
    }

    @Test
    public void deleteById() throws Exception {
        Employee testEmployee = setUpEmployee();
        Employee otherEmployee = setUpAnotherEmployee();
        assertEquals(2, employeeDao.getEmployees().size());
        employeeDao.deleteById(testEmployee.getId());
        assertEquals(1, employeeDao.getEmployees().size());
    }
    @Test
    public void clearAll() throws Exception {
        Employee testEmployee = setUpEmployee();
        Employee otherEmployee = setUpAnotherEmployee();
        employeeDao.clearAll();
        assertEquals(0, employeeDao.getEmployees().size());
    }
}