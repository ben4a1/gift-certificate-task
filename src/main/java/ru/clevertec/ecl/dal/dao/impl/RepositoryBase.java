package ru.clevertec.ecl.dal.dao.impl;

import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import ru.clevertec.ecl.dal.dao.Repository;
import ru.clevertec.ecl.dal.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class RepositoryBase<T extends BaseEntity<K>, K extends Serializable> implements Repository<T, K> {

    private final Class<T> clazz;
    private final SessionFactory sessionFactory;

    @Override
    public Optional<T> findById(K id) {
        @Cleanup var session = sessionFactory.openSession();
        return Optional.ofNullable(session.find(clazz, id));
    }

    @Override
    public List<T> findAll() {
        @Cleanup var session = sessionFactory.openSession();
        var criteriaQuery = session.getCriteriaBuilder().createQuery(clazz);
        criteriaQuery.from(clazz);
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public T create(T object) {
        @Cleanup var session = sessionFactory.openSession();
        session.save(object);
        return object;
    }

    @Override
    public T update(T object) {
        @Cleanup var session = sessionFactory.openSession();
        session.merge(object);
        return object;
    }

    @Override
    public void delete(K id) {
        @Cleanup var session = sessionFactory.openSession();
        session.delete(id);
        session.flush();
    }
}
