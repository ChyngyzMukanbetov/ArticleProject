package com.test.articleproject;

import com.test.articleproject.controller.ArticleRestController;
import com.test.articleproject.converter.ArticleCreateConverter;
import com.test.articleproject.converter.ArticleResponseConverter;
import com.test.articleproject.model.dto.ArticleCreateDto;
import com.test.articleproject.model.dto.ArticleResponseDto;
import com.test.articleproject.model.entity.Article;
import com.test.articleproject.model.entity.User;
import com.test.articleproject.service.ArticleService;
import com.test.articleproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArticleRestControllerTest {
    @Mock
    private ArticleService articleService;
    @Mock
    private UserService userService;
    @Mock
    private Principal principal;
    @Mock
    private ArticleResponseConverter articleResponseConverter;
    @Mock
    private ArticleCreateConverter articleCreateConverter;

    @InjectMocks
    private ArticleRestController articleRestController;

    @Test
    void getAllArticles_ShouldReturnOk() {
        Pageable pageable = PageRequest.of(0, 10);
        Article article = new Article(1L, "title", new User(), "content", LocalDate.now());
        Page<Article> articles = new PageImpl<>(List.of(article));
        when(articleService.findAll(pageable)).thenReturn(articles);
        when(articleResponseConverter.toDto(any(Article.class))).thenReturn(new ArticleResponseDto());
        Page<ArticleResponseDto> response = articleRestController.getAllArticles(pageable);
        assertEquals(1, response.getTotalElements());
        assertEquals(1, response.getNumberOfElements());
        verify(articleService, times(1)).findAll(pageable);
    }

    @Test
    void createArticle_ShouldReturnForbidden_WhenUserIsNotAuthor() {
        ArticleCreateDto articleCreateDto = new ArticleCreateDto();
        articleCreateDto.setTitle("This title is valid");
        articleCreateDto.setDateOfPublishing(LocalDate.now());
        articleCreateDto.setAuthorId(2L);

        User user = new User();
        user.setId(1L);
        user.setUsername("user");
        user.setPassword("password");

        when(userService.findByUsername(principal.getName())).thenReturn(user);
        ResponseEntity<ArticleResponseDto> response = articleRestController.createArticle(articleCreateDto, principal);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        verify(articleService, never()).save(any());
    }

    @Test
    void createArticle_ShouldReturnOk() {
        ArticleCreateDto articleCreateDto = new ArticleCreateDto();
        articleCreateDto.setTitle("Test Title");
        articleCreateDto.setAuthorId(1L);
        articleCreateDto.setContent("Test Content");
        articleCreateDto.setDateOfPublishing(LocalDate.now());

        User user = new User();
        user.setId(1L);
        when(principal.getName()).thenReturn("testuser");
        when(userService.findByUsername("testuser")).thenReturn(user);

        Article article = new Article();
        article.setTitle("Test Title");
        article.setAuthor(user);
        article.setContent("Test Content");
        article.setDateOfPublishing(LocalDate.now());
        when(articleCreateConverter.toModel(articleCreateDto)).thenReturn(article);
        when(articleResponseConverter.toDto(article)).thenReturn(new ArticleResponseDto());
        when(articleService.getById(any())).thenReturn(article);

        ResponseEntity<ArticleResponseDto> response = articleRestController.createArticle(articleCreateDto, principal);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(articleService, times(1)).save(article);
    }
}
