package com.test.articleproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.test.articleproject.controller.StatisticController;
import com.test.articleproject.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StatisticControllerTest {
    @Mock
    private ArticleService articleService;

    @InjectMocks
    private StatisticController statisticController;

    @Test
    public void testGetPublishedArticlesCountByDay() {

        Map<LocalDate, Long> mockData = new HashMap<>();
        mockData.put(LocalDate.now(), 3L);
        mockData.put(LocalDate.now().minusDays(1), 5L);

        when(articleService.getPublishedArticlesCountByDay()).thenReturn(mockData);

        Map<LocalDate, Long> result = statisticController.getPublishedArticlesCountByDay();

        assertEquals(mockData, result);
    }
}
