package com.test.articleproject.converter.resolver;

import com.test.articleproject.model.dto.UserDto;
import com.test.articleproject.model.entity.User;
import com.test.articleproject.service.UserService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserResolver {

    private final UserService userService;

    @ObjectFactory
    public User resolve(Long userId, @TargetType Class<User> type) {
        if (userId == null) {
            return null;
        } else {
            return userService.getById(userId);
        }
    }

    @ObjectFactory
    public User resolve(UserDto userDto, @TargetType Class<User> type) {
        if (userDto == null) {
            return null;
        } else if (userDto.getId() == null) {
            return new User();
        } else {
            return userService.getById(userDto.getId());
        }
    }
}
