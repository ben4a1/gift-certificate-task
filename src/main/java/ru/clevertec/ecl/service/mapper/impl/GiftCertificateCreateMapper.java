package ru.clevertec.ecl.service.mapper.impl;

import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.service.dto.GiftCertificateCreateDto;
import ru.clevertec.ecl.service.mapper.Mapper;


import static java.time.LocalDateTime.*;
import static ru.clevertec.ecl.dal.entity.GiftCertificate.*;

public class GiftCertificateCreateMapper implements Mapper<GiftCertificateCreateDto, GiftCertificate> {
    @Override
    public GiftCertificate map(GiftCertificateCreateDto object) {
        return aGiftCertificate()
                .name(object.name())
                .description(object.description())
                .price(object.price())
                .createDate(now())
                .lastUpdateDate(now())
                .build();
    }
}
