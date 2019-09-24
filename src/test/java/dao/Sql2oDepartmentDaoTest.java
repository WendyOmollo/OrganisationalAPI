package dao;

import models.ClassifiedNews;
import models.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oDepartmentDaoTest {
    private Connection conn;
    private Sql2oClassifiedNewsDao classifiedNewsDao;
    private Sql2oDepartmentDao departmentDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        classifiedNewsDao = new Sql2oClassifiedNewsDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    public ClassifiedNews setupClassifiedNews(){
        ClassifiedNews classifiedNews = new ClassifiedNews("Into the water","A young girl decided to immerse herself in books because she believed she has treasure inside books");
        classifiedNewsDao.add(classifiedNews);
        return classifiedNews;
    }
    public ClassifiedNews setupAnotherClassifiedNews(){
        ClassifiedNews classifiedNews = new ClassifiedNews("Why is the world going towards tech?","Nowadays a lot of people go for robots than humans");
        classifiedNewsDao.add(classifiedNews);
        return classifiedNews;
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
    @Test
    public void setUpDepartment_getsId(){
        Department department = setUpDepartment();
        assertEquals(1,department.getId());
    }
    @Test
    public void getAll() throws Exception{
        Department department = setUpDepartment();
        Department department1 = setUpAnotherDepartment();
        assertEquals(2,departmentDao.getDepartments().size());
    }
    @Test
    public void getAllClassifiedNewsByDepartment() throws Exception {
        ClassifiedNews classifiedNews = setupClassifiedNews();
        classifiedNewsDao.add(classifiedNews);

        ClassifiedNews classifiedNews1 = setupAnotherClassifiedNews();
        classifiedNewsDao.add(classifiedNews1);

        Department testDepartment = setUpDepartment();
        departmentDao.add(testDepartment);
        departmentDao.addClassifiedNewsToDepartment(classifiedNews,testDepartment);
        departmentDao.addClassifiedNewsToDepartment(classifiedNews1,testDepartment);

        ClassifiedNews[] classifieds = {classifiedNews,classifiedNews1};
        assertEquals(Arrays.asList(classifieds), departmentDao.getAllClassifiedNewsByDepartment(testDepartment.getId()));
    }

}