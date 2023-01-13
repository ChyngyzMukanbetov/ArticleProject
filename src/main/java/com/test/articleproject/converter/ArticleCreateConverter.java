package com.test.articleproject.converter;

import com.test.articleproject.model.dto.ArticleCreateDto;
import com.test.articleproject.model.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserConverter.class})
public interface ArticleCreateConverter {


    @Mapping(target = "author", source = "authorId")
    Article toModel(ArticleCreateDto articleResponseDto);

    @Mapping(target = "author", source = "authorId")
    List<Article> toModel(List<ArticleCreateDto> articleResponseDto);
}
