package ru.clevertec.ecl.dal.dao;

import ru.clevertec.ecl.dal.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<T extends BaseEntity<K>, K extends Serializable> {

    Optional<T> findById(K id);
    List<T> findAll();
    T create(T object);
    T update(T object);
    void delete(K id);
}
