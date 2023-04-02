package ru.clevertec.ecl.dal.dao.impl;

import ru.clevertec.ecl.dal.dao.GiftCertificateRepository;
import ru.clevertec.ecl.dal.entity.GiftCertificate;

import javax.persistence.EntityManager;
import java.util.List;

public class GiftCertificateRepositoryHibernate extends RepositoryBase<GiftCertificate, Long> implements GiftCertificateRepository {

    public GiftCertificateRepositoryHibernate(EntityManager entityManager){
        super(GiftCertificate.class, entityManager);
    }


    @Override
    public List<GiftCertificate> findByPartOfName(String partOfName) {
        return null;
    }

    @Override
    public List<GiftCertificate> findByPartOfDescription(String partOfDescription) {
        return null;
    }
}
