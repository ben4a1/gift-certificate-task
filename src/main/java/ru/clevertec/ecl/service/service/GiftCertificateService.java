package ru.clevertec.ecl.service.service;

import ru.clevertec.ecl.dal.entity.GiftCertificate;

import java.util.List;
import java.util.Map;

public interface GiftCertificateService {
    List<GiftCertificate> findAll(Map<String, String> filterParams);

    GiftCertificate findById(Long id);

    GiftCertificate create(GiftCertificate giftCertificate);

    GiftCertificate update(GiftCertificate giftCertificate);

    void delete(Long giftCertificateId);
}
