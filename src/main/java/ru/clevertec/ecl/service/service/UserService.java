package ru.clevertec.ecl.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.dal.dao.UserRepository;
import ru.clevertec.ecl.dal.querydsl.QPredicates;
import ru.clevertec.ecl.service.dto.UserFilter;
import ru.clevertec.ecl.service.dto.UserReadDto;
import ru.clevertec.ecl.service.mapper.impl.UserCreateEditMapper;
import ru.clevertec.ecl.service.mapper.impl.UserReadMapper;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
        var predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .build();

        return userRepository.findAll(predicate, pageable)
                .map(userReadMapper::map);
    }
}
