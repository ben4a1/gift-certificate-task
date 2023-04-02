package ru.clevertec.ecl.dal.dao.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.clevertec.ecl.dal.dao.Repository;
import ru.clevertec.ecl.dal.entity.BaseEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class RepositoryBase<T extends BaseEntity<K>, K extends Serializable> implements Repository<T, K> {

    private final Class<T> clazz;
    @Getter
    private final EntityManager entityManager;

    @Override
    public Optional<T> findById(K id, Map<String, Object> properties) {
        return Optional.ofNullable(entityManager.find(clazz, id, properties));
    }

    @Override
    public List<T> findAll() {
        var criteriaQuery = entityManager.getCriteriaBuilder().createQuery(clazz);
        criteriaQuery.from(clazz);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public T create(T object) {
        entityManager.persist(object);
        return object;
    }

    @Override
    public T update(T object) {
        entityManager.merge(object);
        return object;
    }

    @Override
    public void delete(K id) {
        entityManager.remove(id);
        entityManager.flush();
    }
}
