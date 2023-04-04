package ru.clevertec.ecl.service.service.impl;

import lombok.RequiredArgsConstructor;
import ru.clevertec.ecl.dal.dao.impl.RepositoryBase;
import ru.clevertec.ecl.dal.entity.BaseEntity;
import ru.clevertec.ecl.service.service.Service;

import java.io.Serializable;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class ServiceBase<E extends BaseEntity<K>, K extends Serializable> implements Service<E, K> {

    private final RepositoryBase<E, K> repositoryBase;
    @Override
    public boolean delete(K id) {
        Optional<E> maybe = repositoryBase.findById(id);
        maybe.ifPresent(object -> repositoryBase.delete(object.getId()));
        return maybe.isPresent();
    }
}
