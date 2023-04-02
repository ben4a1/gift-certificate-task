package ru.clevertec.ecl.dal.dao.impl;

import org.hibernate.SessionFactory;
import ru.clevertec.ecl.dal.entity.Tag;

public class TagRepositoryHibernate extends RepositoryBase<Tag, Long>{
    public TagRepositoryHibernate(SessionFactory sessionFactory){
        super(Tag.class, sessionFactory);
    }
}
