package ru.clevertec.ecl.dal.dao.impl;

import org.hibernate.SessionFactory;
import ru.clevertec.ecl.dal.entity.GiftCertificate;

import java.util.List;

public class GiftCertificateRepositoryHibernate extends RepositoryBase<GiftCertificate, Long> {

    public GiftCertificateRepositoryHibernate(SessionFactory sessionFactory){
        super(GiftCertificate.class, sessionFactory);
    }


    public List<GiftCertificate> findByPartOfName(String partOfName) {
        return null;
    }

    public List<GiftCertificate> findByPartOfDescription(String partOfDescription) {
        return null;
    }
}
