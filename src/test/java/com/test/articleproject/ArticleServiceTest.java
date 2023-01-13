package com.test.articleproject;

import com.test.articleproject.dao.ArticleDao;
import com.test.articleproject.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {
    @Mock
    private ArticleDao articleDao;

    @InjectMocks
    private ArticleService articleService;

    @Test
    public void getPublishedArticlesCountByDay_ShouldReturnMapOfCountsByDay_WhenCalled() {
        LocalDate now = LocalDate.now();
        when(articleDao.countAllByDateOfPublishing(now.minusDays(0))).thenReturn(3L);
        when(articleDao.countAllByDateOfPublishing(now.minusDays(1))).thenReturn(2L);
        when(articleDao.countAllByDateOfPublishing(now.minusDays(2))).thenReturn(5L);
        when(articleDao.countAllByDateOfPublishing(now.minusDays(3))).thenReturn(1L);
        when(articleDao.countAllByDateOfPublishing(now.minusDays(4))).thenReturn(7L);
        when(articleDao.countAllByDateOfPublishing(now.minusDays(5))).thenReturn(4L);
        when(articleDao.countAllByDateOfPublishing(now.minusDays(6))).thenReturn(6L);

        Map<LocalDate, Long> countsByDay = articleService.getPublishedArticlesCountByDay();

        assertEquals(3L, countsByDay.get(now.minusDays(0)));
        assertEquals(2L, countsByDay.get(now.minusDays(1)));
        assertEquals(5L, countsByDay.get(now.minusDays(2)));
        assertEquals(1L, countsByDay.get(now.minusDays(3)));
        assertEquals(7L, countsByDay.get(now.minusDays(4)));
        assertEquals(4L, countsByDay.get(now.minusDays(5)));
        assertEquals(6L, countsByDay.get(now.minusDays(6)));
    }
}
