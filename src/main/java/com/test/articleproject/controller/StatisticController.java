package com.test.articleproject.controller;

import com.test.articleproject.service.ArticleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticController {
    private final ArticleService articleService;

    public StatisticController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/7dayscount")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Map<LocalDate, Long> getPublishedArticlesCountByDay() {
        return articleService.getPublishedArticlesCountByDay();
    }
}
