package ru.clevertec.ecl.service.mapper.impl;

import ru.clevertec.ecl.dal.entity.User;
import ru.clevertec.ecl.service.dto.UserReadDto;
import ru.clevertec.ecl.service.mapper.Mapper;

public class UserReadMapper implements Mapper<User, UserReadDto> {
    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(
                object.getId(),
                object.getUsername(),
                object.getBirthDate(),
                object.getFirstname(),
                object.getLastname(),
                object.getRole()
        );
    }
}
