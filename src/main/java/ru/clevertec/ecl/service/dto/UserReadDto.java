package ru.clevertec.ecl.service.dto;

import lombok.Value;
import ru.clevertec.ecl.dal.entity.Role;

import java.time.LocalDate;

@Value
public class UserReadDto {

    Long id;
    String username;
    LocalDate birthDate;
    String firstname;
    String lastname;
    Role role;
}
