package dao;

import models.ClassifiedNews;
import models.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.concurrent.Callable;

import static org.junit.Assert.*;

public class Sql2oClassifiedNewsDaoTest {

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
    @Test
    public void addingClassifiedNewsSetsId() throws Exception {
        ClassifiedNews testClassified = setupAnotherClassifiedNews();
        assertEquals(1, testClassified.getId());
    }
    @Test
    public void getAll() throws Exception {
        ClassifiedNews classifiedNews1 = setupClassifiedNews();
        ClassifiedNews classifiedNews2 = setupAnotherClassifiedNews();
        assertEquals(2, classifiedNewsDao.getClassifiedNews().size());
    }

@Test
    public void getAllDepartmentsForAClassifiedNews(){
        Department department = setUpDepartment();
        departmentDao.add(department);
        ClassifiedNews classifiedNews = setupClassifiedNews();
        classifiedNewsDao.add(classifiedNews);
        classifiedNewsDao.addClassifiedNewsToDepartment(classifiedNews,department);
        assertEquals(1, classifiedNewsDao.getAllDepartmentsForAClassifiedNews(classifiedNews.getId()).size());
    }
    @Test
    public void deleteById() throws Exception {
        ClassifiedNews testClassifiedNews = setupClassifiedNews();
        ClassifiedNews otherClassifiedNews = setupAnotherClassifiedNews();
        assertEquals(2, classifiedNewsDao.getClassifiedNews().size());
        classifiedNewsDao.deleteById(testClassifiedNews.getId());
        assertEquals(1, classifiedNewsDao.getClassifiedNews().size());
    }
    @Test
    public void clearAll() throws Exception {
        ClassifiedNews testClassifiedNews = setupClassifiedNews();
        ClassifiedNews otherClassifiedNews = setupAnotherClassifiedNews();
        classifiedNewsDao.clearAll();
        assertEquals(0, classifiedNewsDao.getClassifiedNews().size());
    }
}