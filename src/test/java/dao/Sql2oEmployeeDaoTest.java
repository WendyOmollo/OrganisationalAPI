package dao;

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
    @Test
    public void addingEmployeeSetsId() throws Exception {
        Employee testEmployee = setUpEmployee();
        assertEquals(1, testEmployee.getId());
    }

}