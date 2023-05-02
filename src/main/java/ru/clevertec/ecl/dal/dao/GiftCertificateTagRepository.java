package ru.clevertec.ecl.dal.dao;

public interface GiftCertificateTagRepository {

    boolean isTagExists(Long giftCertificateId, Long tagId);
    void addDependency(Long giftCertificateId, Long tagId);
}
