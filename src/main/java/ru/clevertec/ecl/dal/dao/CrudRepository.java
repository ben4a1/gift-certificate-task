package ru.clevertec.ecl.dal.dao;

import java.util.List;

public interface CrudRepository<T, K> {

    T findById(K id);
    List<T> findAll();
    T create(T object);
    T update(T object);
    void delete(K id);
}
