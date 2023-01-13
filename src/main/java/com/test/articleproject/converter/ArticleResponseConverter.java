package com.test.articleproject.converter;

import com.test.articleproject.model.dto.ArticleResponseDto;
import com.test.articleproject.model.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleResponseConverter {

    @Mapping(source = "author.username", target = "authorUsername")
    ArticleResponseDto toDto(Article article);

    @Mapping(source = "author.username", target = "authorUsername")
    List<ArticleResponseDto> toDto(List<Article> article);
}