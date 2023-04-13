package ru.clevertec.ecl.dal.dao;

import ru.clevertec.ecl.dal.entity.Role;
import ru.clevertec.ecl.dal.entity.User;
import ru.clevertec.ecl.service.dto.PersonalInfo;
import ru.clevertec.ecl.service.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);

    List<PersonalInfo> findAllByGiftCertificateIdAndRole(Long certificateId, Role role);

    void updateGiftCertificateAndRole(List<User> users);

    void updateGiftCertificateAndRoleNamed(List<User> users);
}
