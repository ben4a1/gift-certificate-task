package ru.clevertec.ecl.service.service;

import lombok.RequiredArgsConstructor;
import ru.clevertec.ecl.dal.dao.impl.GiftCertificateRepository;
import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.service.dto.GiftCertificateCreateDto;
import ru.clevertec.ecl.service.dto.GiftCertificateDto;
import ru.clevertec.ecl.service.dto.GiftCertificateReadDto;
import ru.clevertec.ecl.service.mapper.hibernate.GiftCertificateCreateMapper;
import ru.clevertec.ecl.service.mapper.hibernate.GiftCertificateReadMapper;
import ru.clevertec.ecl.service.mapper.Mapper;

import javax.transaction.Transactional;
import javax.validation.*;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class GiftCertificateService {

    private final GiftCertificateRepository giftCertificateRepository;
    private final GiftCertificateReadMapper giftCertificateReadMapper;
    private final GiftCertificateCreateMapper giftCertificateCreateMapper;

    @Transactional
    public Long create(GiftCertificateCreateDto giftCertificateDto) {
        var validatorFactory = Validation.buildDefaultValidatorFactory();
        var validator = validatorFactory.getValidator();
        var validationResult = validator.validate(giftCertificateDto);
        if (!validationResult.isEmpty()) {
            throw new ConstraintViolationException(validationResult);
        }
        GiftCertificate giftCertificate = giftCertificateCreateMapper.mapFrom(giftCertificateDto);
        return giftCertificateRepository.create(giftCertificate).getId();
    }

    @Transactional
    public <T> Optional<T> findById(Long id, Mapper<GiftCertificate, T> mapper) {
        return giftCertificateRepository.findById(id)
                .map(mapper::mapFrom);
    }

    @Transactional
    public Optional<GiftCertificateReadDto> findById(Long id) {
        return findById(id, giftCertificateReadMapper);
    }

    @Transactional
    public boolean delete(Long id) {
        var maybeCertificate = giftCertificateRepository.findById(id);
        maybeCertificate.ifPresent(giftCertificate -> giftCertificateRepository.delete(giftCertificate.getId()));
        return maybeCertificate.isPresent();
    }

    public List<GiftCertificateReadDto> findAll() {
        return giftCertificateRepository.findAll().stream().map(giftCertificateReadMapper::mapFrom).toList();
    }

    public GiftCertificateReadDto update(GiftCertificate certificate) {
        return giftCertificateReadMapper.mapFrom(giftCertificateRepository.update(certificate));
    }
}
