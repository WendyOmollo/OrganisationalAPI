package dao;

import models.Department;
import models.News;

import java.util.List;

public interface NewsDao {

    void add(News news);

    List<News> getNews();

    void deleteById(int id);
    void clearAll();
}
