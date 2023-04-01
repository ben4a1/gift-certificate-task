package ru.clevertec.ecl.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.ecl.dal.dto.GiftCertificateDto;
import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.dal.entity.Tag;
import ru.clevertec.ecl.dal.dao.GiftCertificateRepository;
import ru.clevertec.ecl.dal.dao.GiftCertificateTagRepository;
import ru.clevertec.ecl.dal.dao.TagRepository;
import ru.clevertec.ecl.service.mapper.GiftCertificateMapper;
import ru.clevertec.ecl.service.service.GiftCertificateService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private GiftCertificateRepository giftCertificateRepository;
    private TagRepository tagRepository;
    private GiftCertificateTagRepository giftCertificateTagRepository;
    private GiftCertificateMapper giftMapper;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateRepository giftCertificateRepository, TagRepository tagRepository, GiftCertificateTagRepository giftCertificateTagRepository, GiftCertificateMapper giftMapper) {
        this.giftCertificateRepository = giftCertificateRepository;
        this.tagRepository = tagRepository;
        this.giftCertificateTagRepository = giftCertificateTagRepository;
        this.giftMapper = giftMapper;
    }

    @Override
    public List<GiftCertificateDto> findAll() {
        return giftCertificateRepository.findAll()
                .stream().map(giftCertificate ->
                        giftMapper.toCertificateDTO(giftCertificate)).toList();
    }

    @Override
    public GiftCertificateDto findById(Long id) {
        GiftCertificateDto giftCertificateDto = giftMapper.toCertificateDTO(giftCertificateRepository.findById(id));
        return giftCertificateDto;
    }

    @Override
    public List<GiftCertificateDto> findByPartOfName(String partOfName) {
        List<GiftCertificate> byPartOfName = giftCertificateRepository.findByPartOfName(partOfName);
        return byPartOfName.stream().map(giftCertificate ->
                giftMapper.toCertificateDTO(giftCertificate)).toList();
    }

    @Override
    public List<GiftCertificateDto> findByPartOfDescription(String partOfDescription) {
        List<GiftCertificate> byPartOfDescription = giftCertificateRepository.findByPartOfDescription(partOfDescription);
        return byPartOfDescription.stream().map(giftCertificate ->
                giftMapper.toCertificateDTO(giftCertificate)).toList();
    }

    @Override
    public GiftCertificateDto create(GiftCertificate giftCertificate) {
        LocalDateTime currentTime = LocalDateTime.now();
        giftCertificate.setCreateDate(currentTime);
        GiftCertificate newGiftCertificate = giftCertificateRepository.create(giftCertificate);
        if (giftCertificate.getTagList() != null) {
            addTags(newGiftCertificate.getId(), giftCertificate.getTagList());
        }
        return giftMapper.toCertificateDTO(newGiftCertificate);
    }

    @Override
    public GiftCertificateDto update(GiftCertificate giftCertificate) {
        LocalDateTime currentTime = LocalDateTime.now();
        giftCertificate.setLastUpdateDate(currentTime);
        if (giftCertificate.getTagList() != null) {
            addTags(giftCertificate.getId(), giftCertificate.getTagList());
        }
        return giftMapper.toCertificateDTO(giftCertificateRepository.update(giftCertificate));
    }

    @Override
    public void delete(Long giftCertificateId) {
        giftCertificateRepository.delete(giftCertificateId);
    }

    private void addTags(Long giftCertificateId, List<Tag> tags) {
        tags.forEach(tag -> {
            Tag newTag;
            Long id;
            if (!tagRepository.isTagExists(tag)) {
                newTag = tagRepository.create(tag);
                id = newTag.getId();
            } else {
                id = tagRepository
                        .findTagByName(tag.getName()).getId();
            }
            if (!giftCertificateTagRepository.isTagExists(giftCertificateId, id)) {
                giftCertificateTagRepository.addDependency(giftCertificateId, id);
            }
        });
    }
}
