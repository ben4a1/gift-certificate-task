package ru.clevertec.ecl.dal.repository;

import ru.clevertec.ecl.dal.entity.GiftCertificate;

import java.util.List;

public interface GiftCertificateRepository extends CrudRepository<GiftCertificate, Long> {

    List<GiftCertificate> findBySpecificRequest(String specificRequest);
}
