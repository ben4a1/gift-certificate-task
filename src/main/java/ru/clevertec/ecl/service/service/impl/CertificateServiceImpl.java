package ru.clevertec.ecl.service.service.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.graph.GraphSemantic;
import ru.clevertec.ecl.dal.dao.impl.GiftCertificateRepositoryHibernate;
import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.service.dto.GiftCertificateDto;
import ru.clevertec.ecl.service.mapper.GiftCertificateMapper;
import ru.clevertec.ecl.service.service.CertificateService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final GiftCertificateMapper giftCertificateMapper;
    private final GiftCertificateRepositoryHibernate giftRepository;

    @Transactional
    @Override
    public Long create(GiftCertificateDto giftCertificateDto) {
        GiftCertificate giftCertificate = giftCertificateMapper.toCertificate(giftCertificateDto);
        return giftRepository.create(giftCertificate).getId();
    }

    @Transactional
    @Override
    public Optional<GiftCertificateDto> findById(Long id) {
        return giftRepository.findById(id)
                .map(giftCertificateMapper::toCertificateDTO);
    }

    @Override
    public List<GiftCertificateDto> findAll() {
        return giftRepository.findAll().stream().map(giftCertificateMapper::toCertificateDTO).toList();
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        var maybeCertificate = giftRepository.findById(id);
        maybeCertificate.ifPresent(giftCertificate -> giftRepository.delete(giftCertificate.getId()));
        return maybeCertificate.isPresent();
    }
}
