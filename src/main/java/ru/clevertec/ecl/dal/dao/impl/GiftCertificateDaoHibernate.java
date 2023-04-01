package ru.clevertec.ecl.dal.dao.impl;

import ru.clevertec.ecl.dal.dao.GiftCertificateRepository;
import ru.clevertec.ecl.dal.entity.GiftCertificate;

import java.util.List;

public class GiftCertificateDaoHibernate implements GiftCertificateRepository {
    @Override
    public GiftCertificate findById(Long id) {
        return null;
    }

    @Override
    public List<GiftCertificate> findAll() {
        return null;
    }

    @Override
    public GiftCertificate create(GiftCertificate object) {
        return null;
    }

    @Override
    public GiftCertificate update(GiftCertificate object) {
        return null;
    }

    @Override
    public void delete(Long id) {

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
