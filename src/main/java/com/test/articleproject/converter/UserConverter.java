package com.test.articleproject.converter;

import com.test.articleproject.converter.resolver.UserResolver;
import com.test.articleproject.model.dto.UserDto;
import com.test.articleproject.model.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ArticleResponseConverter.class, UserResolver.class})
public interface UserConverter {

    User toUser(Long userId);

    UserDto toDto(User user);

    List<UserDto> toDto(List<User> user);

    User toModel(UserDto userDto);

    List<User> toModel(List<UserDto> userDto);
}
