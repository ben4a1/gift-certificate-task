package ru.clevertec.ecl.dal.repository;

import ru.clevertec.ecl.dal.entity.GiftCertificate;

import java.util.List;
import java.util.Map;

public interface GiftCertificateRepository extends CrudRepository<GiftCertificate, Long> {

    List<GiftCertificate> findAllWithFilter(String specificRequest);
}