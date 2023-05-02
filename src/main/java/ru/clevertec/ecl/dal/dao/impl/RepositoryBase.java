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
public abstract class RepositoryBase<E extends BaseEntity<K>, K extends Serializable> implements Repository<E, K> {

    private final Class<E> clazz;
    @Getter
    private final EntityManager entityManager;

    @Override
    public Optional<E> findById(K id, Map<String, Object> properties) {
        return Optional.ofNullable(entityManager.find(clazz, id, properties));
    }

    @Override
    public List<E> findAll() {
        var criteriaQuery = entityManager.getCriteriaBuilder().createQuery(clazz);
        criteriaQuery.from(clazz);
        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }

    @Override
    public E create(E object) {
        entityManager.persist(object);
        return object;
    }

    @Override
    public E update(E object) {
        return entityManager.merge(object);
    }

    @Override
    public void delete(K id) {
        entityManager.remove(id);
        entityManager.flush();
    }
}
