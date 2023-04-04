package ru.clevertec.ecl.service.service;

import ru.clevertec.ecl.dal.entity.Tag;

import java.util.List;

public interface TagServiceToDelete {

    List<Tag> findAll();
    Tag create(Tag tag);
    Tag update(Tag tag);
    void delete(Long id);
}
