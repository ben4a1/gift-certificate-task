package ru.clevertec.ecl.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.dal.entity.Tag;
import ru.clevertec.ecl.dal.repository.GiftCertificateRepository;
import ru.clevertec.ecl.dal.repository.GiftCertificateTagRepository;
import ru.clevertec.ecl.dal.repository.TagRepository;
import ru.clevertec.ecl.service.service.GiftCertificateService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private GiftCertificateRepository giftCertificateRepository;
    private TagRepository tagRepository;
    private GiftCertificateTagRepository giftCertificateTagRepository;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateRepository giftCertificateRepository, TagRepository tagRepository, GiftCertificateTagRepository giftCertificateTagRepository) {
        this.giftCertificateRepository = giftCertificateRepository;
        this.tagRepository = tagRepository;
        this.giftCertificateTagRepository = giftCertificateTagRepository;
    }

    @Override
    public List<GiftCertificate> findAll(Map<String, String> filterParams) {
        List<GiftCertificate> giftCertificates;
        if (filterParams.size() == 0) {
            return giftCertificateRepository.findAll();
        } else {
            giftCertificates = findAllWithFilter(filterParams);
        }
        if (giftCertificates != null) {
            giftCertificates.forEach(giftCertificate ->
                    giftCertificate.setTagList(tagRepository.findAllTagByCertificateId(giftCertificate.getId())));
        }
        return giftCertificates;
    }

    @Override
    public GiftCertificate findById(Long id) {
        return giftCertificateRepository.findById(id);
    }

    @Override
    public GiftCertificate create(GiftCertificate giftCertificate) {
        LocalDateTime currentTime = LocalDateTime.now();
        giftCertificate.setCreateDate(currentTime);
        GiftCertificate newGiftCertificate = giftCertificateRepository.create(giftCertificate);
        if (giftCertificate.getTagList() != null) {
            addTags(newGiftCertificate.getId(), giftCertificate.getTagList());
        }
        return newGiftCertificate;
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        LocalDateTime currentTime = LocalDateTime.now();
        giftCertificate.setLastUpdateDate(currentTime);
        if (giftCertificate.getTagList() != null){
           addTags(giftCertificate.getId(), giftCertificate.getTagList());
        }
        return giftCertificateRepository.update(giftCertificate);
    }

    @Override
    public void delete(Long giftCertificateId) {
        giftCertificateRepository.delete(giftCertificateId);
    }

    private List<GiftCertificate> findAllWithFilter(Map<String, String> filterParams) {
        return null;
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
