package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    public Employee setUpEmployee(){
        Employee employee = new Employee("Michael","Managing Director");
        return employee;
    }
    @Test
    public void addEmployee_getsName(){
        Employee employee = setUpEmployee();
        assertEquals("Michael",employee.getName());
    }
    @Test
    public void addEmployee_getPosition(){
        Employee employee = setUpEmployee();
        assertEquals("Managing Director",employee.getPosition());
    }

}