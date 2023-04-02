package ru.clevertec.ecl.dal.dao;

import ru.clevertec.ecl.dal.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.*;

public interface Repository<T extends BaseEntity<K>, K extends Serializable> {

    default Optional<T> findById(K id){
        return findById(id, emptyMap());
    }

    Optional<T> findById(K id, Map<String, Object> properties);

    List<T> findAll();

    T create(T object);

    T update(T object);

    void delete(K id);
}
