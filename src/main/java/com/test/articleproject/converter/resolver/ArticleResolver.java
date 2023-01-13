//package com.test.articleproject.converter.resolver;
//
//import com.test.articleproject.model.dto.ArticleCreateDto;
//import com.test.articleproject.model.entity.Article;
//import com.test.articleproject.service.ArticleService;
//import lombok.AllArgsConstructor;
//import org.mapstruct.ObjectFactory;
//import org.mapstruct.TargetType;
//import org.springframework.stereotype.Component;
//
//@Component
//@AllArgsConstructor
//public class ArticleResolver {
//    private final ArticleService articleService;
//
//    @ObjectFactory
//    public Article resolve(ArticleCreateDto dto, @TargetType Class<Article> type) {
//        Article article;
//        if (dto == null) {
//            return null;
//        } else if (dto.getId() == null) {
//            article = new Article();
//        } else {
//            article = articleService.getById(dto.getId());
//        }
//        return article;
//    }
//}
