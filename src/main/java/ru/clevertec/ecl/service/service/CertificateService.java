package ru.clevertec.ecl.service.service;


import ru.clevertec.ecl.service.dto.GiftCertificateDto;

import java.util.List;
import java.util.Optional;

public interface CertificateService {


    Long create(GiftCertificateDto giftCertificateDto);
    Optional<GiftCertificateDto> findById(Long id);
    List<GiftCertificateDto> findAll();
    boolean delete(Long id);
}
