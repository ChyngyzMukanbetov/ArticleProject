package com.test.articleproject.controller;

import com.test.articleproject.converter.ArticleCreateConverter;
import com.test.articleproject.converter.ArticleResponseConverter;
import com.test.articleproject.model.dto.ArticleCreateDto;
import com.test.articleproject.model.dto.ArticleResponseDto;
import com.test.articleproject.model.entity.Article;
import com.test.articleproject.service.ArticleService;
import com.test.articleproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/articles")
@AllArgsConstructor
@Validated
public class ArticleRestController {

    private final ArticleService articleService;
    private final UserService userService;
    private ArticleResponseConverter articleResponseConverter;
    private ArticleCreateConverter articleCreateConverter;

    @GetMapping(value = "/getall")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Page<ArticleResponseDto> getAllArticles(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return articleService.findAll(pageable).map(articleResponseConverter::toDto);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ArticleResponseDto> createArticle(@Valid @RequestBody ArticleCreateDto articleCreateDto, Principal principal) {
        if (articleCreateDto.getAuthorId() != userService.findByUsername(principal.getName()).getId()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Article article = articleCreateConverter.toModel(articleCreateDto);
        articleService.save(article);
        return new ResponseEntity<>(articleResponseConverter.toDto(articleService.getById(article.getId())), HttpStatus.OK);
    }
}