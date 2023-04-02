package ru.clevertec.ecl.dal.dao.impl;

import ru.clevertec.ecl.dal.entity.Tag;

import javax.persistence.EntityManager;

public class TagRepositoryHibernate extends RepositoryBase<Tag, Long>{
    public TagRepositoryHibernate(EntityManager entityManager){
        super(Tag.class, entityManager);
    }
}
