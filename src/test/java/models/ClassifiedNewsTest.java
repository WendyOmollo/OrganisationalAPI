package models;

import org.junit.After;
import org.junit.Before;
import dao.Sql2oClassifiedNewsDao;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClassifiedNewsTest {

    private Sql2oClassifiedNewsDao classifiedNewsDao;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    public ClassifiedNews setupClassifiedNews(){
        ClassifiedNews classifiedNews = new ClassifiedNews("Into the water","A young girl decided to immerse herself in books because she believed she has treasure inside books");
        return classifiedNews;
    }
    public ClassifiedNews setupAnotherClassifiedNews(){
        ClassifiedNews classifiedNews = new ClassifiedNews("Why is the world going towards tech?","Nowadays a lot of people go for robots than humans");
        return classifiedNews;
    }
    @Test
    public void addingClassifiedNewsSetsTitle() throws Exception {
        ClassifiedNews testClassified = setupClassifiedNews();
        assertEquals("Into the water", testClassified.getTitle());
    }
    @Test
            public void addingClassifiedNewsSetsDetails() throws Exception{
        ClassifiedNews testClassified = setupClassifiedNews();
        assertEquals("A young girl decided to immerse herself in books because she believed she has treasure inside books",testClassified.getDetails());

    }

}