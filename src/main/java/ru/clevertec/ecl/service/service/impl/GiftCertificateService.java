package ru.clevertec.ecl.service.service.impl;

import ru.clevertec.ecl.dal.dao.impl.GiftCertificateRepository;
import ru.clevertec.ecl.dal.entity.GiftCertificate;

public class GiftCertificateService extends ServiceBase<GiftCertificate, Long> {
    public GiftCertificateService(GiftCertificateRepository giftCertificateRepository) {
        super(giftCertificateRepository);
    }
}
