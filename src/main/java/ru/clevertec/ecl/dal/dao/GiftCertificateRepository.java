package ru.clevertec.ecl.dal.dao;

import ru.clevertec.ecl.dal.entity.GiftCertificate;

import java.util.List;

public interface GiftCertificateRepository extends Repository<GiftCertificate, Long> {

    List<GiftCertificate> findByPartOfName(String partOfName);

    List<GiftCertificate> findByPartOfDescription(String partOfDescription);
}