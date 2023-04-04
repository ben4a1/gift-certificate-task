package ru.clevertec.ecl.service.service;

import lombok.RequiredArgsConstructor;
import ru.clevertec.ecl.dal.dao.impl.GiftCertificateRepository;
import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.service.dto.GiftCertificateCreateDto;
import ru.clevertec.ecl.service.dto.GiftCertificateReadDto;
import ru.clevertec.ecl.service.mapper.GiftCertificateCreateMapper;
import ru.clevertec.ecl.service.mapper.GiftCertificateReadMapper;
import ru.clevertec.ecl.service.mapper.Mapper;

import java.util.Optional;

@RequiredArgsConstructor
public class GiftCertificateService {
    private final GiftCertificateRepository giftCertificateRepository;
    private final GiftCertificateReadMapper giftCertificateReadMapper;
    private final GiftCertificateCreateMapper giftCertificateCreateMapper;

public Long create(GiftCertificateCreateDto giftCertificateDto){
    //validation
    GiftCertificate giftCertificate = giftCertificateCreateMapper.mapFrom(giftCertificateDto);
    return giftCertificateRepository.create(giftCertificate).getId();
}
    public <T> Optional<T> findById(Long id, Mapper<GiftCertificate, T> mapper){
        return giftCertificateRepository.findById(id)
                .map(mapper::mapFrom);
    }
    public Optional<GiftCertificateReadDto> findById(Long id){
        return findById(id, giftCertificateReadMapper);
    }
    public boolean delete(Long id) {
        var maybeCertificate = giftCertificateRepository.findById(id);
        maybeCertificate.ifPresent(giftCertificate -> giftCertificateRepository.delete(giftCertificate.getId()));
        return maybeCertificate.isPresent();
    }

}
