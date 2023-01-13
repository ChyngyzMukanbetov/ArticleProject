package com.test.articleproject.dao;

import com.test.articleproject.model.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface ArticleDao extends JpaRepository<Article, Long> {

//    List<Article> findAllByDateOfPublishingBetween(LocalDate localDateStart, LocalDate localDateEnd);

    Page<Article> findAll(Pageable pageable);

//    int countAllByDateOfPublishingAfter(LocalDate localDate);

    Long countAllByDateOfPublishing(LocalDate localDate);

}