package ru.clevertec.ecl.service.mapper.impl;

import org.springframework.stereotype.Component;
import ru.clevertec.ecl.dal.entity.User;
import ru.clevertec.ecl.service.dto.UserCreateEditDto;
import ru.clevertec.ecl.service.mapper.Mapper;

@Component
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {
    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(UserCreateEditDto object, User user) {
        user.setUsername(object.getUsername());
        user.setFirstname(object.getFirstname());
        user.setLastname(object.getLastname());
        user.setBirthDate(object.getBirthDate());
        user.setRole(object.getRole());
    }
}
