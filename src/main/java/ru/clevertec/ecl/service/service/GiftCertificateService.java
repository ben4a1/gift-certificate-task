package ru.clevertec.ecl.service.service;

import ru.clevertec.ecl.service.dto.GiftCertificateDto;
import ru.clevertec.ecl.dal.entity.GiftCertificate;

import java.util.List;

public interface GiftCertificateService {
    List<GiftCertificateDto> findAll();

    GiftCertificateDto findById(Long id);

    List<GiftCertificateDto> findByPartOfName(String partOfName);

    List<GiftCertificateDto> findByPartOfDescription(String partOfDescription);

    GiftCertificateDto create(GiftCertificate giftCertificate);

    GiftCertificateDto update(GiftCertificate giftCertificate);

    void delete(Long giftCertificateId);
}
