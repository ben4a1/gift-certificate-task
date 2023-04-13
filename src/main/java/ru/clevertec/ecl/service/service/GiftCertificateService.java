package ru.clevertec.ecl.service.service;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.ecl.dal.dao.GiftCertificateRepository;
import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.service.dto.GiftCertificateCreateDto;
import ru.clevertec.ecl.service.dto.GiftCertificateReadDto;
import ru.clevertec.ecl.service.mapper.impl.GiftCertificateCreateMapper;
import ru.clevertec.ecl.service.mapper.Mapper;

import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.service.mapper.impl.GiftCertificateReadMapper;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
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
        GiftCertificate giftCertificate = giftCertificateCreateMapper.map(giftCertificateDto);
        return giftCertificateRepository.save(giftCertificate).getId();
    }

    @Transactional
    public <T> Optional<T> findById(Long id, Mapper<GiftCertificate, T> mapper) {
        return giftCertificateRepository.findById(id)
                .map(mapper::map);
    }

    @Transactional
    public Optional<GiftCertificateReadDto> findById(Long id) {
        return findById(id, giftCertificateReadMapper);
    }

    @Transactional
    public boolean delete(Long id) {
        var maybeCertificate = giftCertificateRepository.findById(id);
        maybeCertificate.ifPresent(giftCertificateRepository::delete);
        return maybeCertificate.isPresent();
    }

    public List<GiftCertificateReadDto> findAll() {
        return giftCertificateRepository.findAll().stream().map(giftCertificateReadMapper::map).toList();
    }

    public GiftCertificateReadDto update(GiftCertificate certificate) {
        return giftCertificateReadMapper.map(giftCertificateRepository.save(certificate));
    }
}
