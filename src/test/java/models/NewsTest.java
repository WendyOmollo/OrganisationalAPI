package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void addNews_getNewsTitle(){
        News news = new News("The world is changing","It is really changing");
        assertEquals("The world is changing",news.getTitle());
    }
    @Test
    public void addNews_getNewsDetails(){
        News news = new News("The world is changing","It is really changing");
        assertEquals("It is really changing",news.getDetails());
    }
    @Test
    public void addNews_getAllNews(){
        News news = new News("The world is changing","It is really changing");
        News anotherNews = new News("Not my own","So he just decided to leave out of nowhere");
        assertNotEquals(news,anotherNews);
    }
}