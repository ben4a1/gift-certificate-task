package ru.clevertec.ecl.service.service;


import ru.clevertec.ecl.service.dto.GiftCertificateDto;

import java.util.Optional;

public interface CertificateService {


    Long create(GiftCertificateDto giftCertificateDto);
    boolean delete(Long id);
    Optional<GiftCertificateDto> findById(Long id);
}
