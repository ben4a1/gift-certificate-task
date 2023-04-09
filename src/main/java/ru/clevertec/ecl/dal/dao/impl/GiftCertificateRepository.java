package ru.clevertec.ecl.dal.dao.impl;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import ru.clevertec.ecl.dal.entity.GiftCertificate;

import java.util.List;

public class GiftCertificateRepository extends RepositoryBase<GiftCertificate, Long> {

    public GiftCertificateRepository(EntityManager entityManager) {
        super(GiftCertificate.class, entityManager);
    }

    List<GiftCertificate> findByPartOfName(String partOfName) {
        return null;
    }

    List<GiftCertificate> findByPartOfDescription(String partOfDescription) {
        return null;
    }
}

