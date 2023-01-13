package com.test.articleproject.service;

import com.test.articleproject.dao.ArticleDao;
import com.test.articleproject.model.entity.Article;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleDao articleDao;

//    public int countAllByDateOfPublishingAfter() {
//        return articleDao.countAllByDateOfPublishingAfter(LocalDate.now().minusDays(6L));
//    }

    public Page<Article> findAll(Pageable pageable) { return articleDao.findAll(pageable); }

    public Map<LocalDate, Long> getPublishedArticlesCountByDay() {
        LocalDate sevenDaysAgo = LocalDate.now().minusDays(6);
        Map<LocalDate, Long> countsByDay = new HashMap<>();
        for (int i = 0; i<7; i++) {
            countsByDay.put(LocalDate.now().minusDays(i), articleDao.countAllByDateOfPublishing(LocalDate.now().minusDays(i)));
        }
        return countsByDay;
    }

    public List<Article> findAll() { return articleDao.findAll(); }

    public void save(Article article) {
        articleDao.save(article);
    }

    public void update(Article article) {
        articleDao.save(article);
    }

    public void delete(Article article) {
        articleDao.delete(article);
    }

    public Article getById(Long id) {
        return articleDao.getById(id);
    }
}
