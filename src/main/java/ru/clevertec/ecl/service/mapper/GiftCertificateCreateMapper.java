package ru.clevertec.ecl.service.mapper;

import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.service.dto.GiftCertificateCreateDto;


import static java.time.LocalDateTime.*;
import static ru.clevertec.ecl.dal.entity.GiftCertificate.*;

public class GiftCertificateCreateMapper implements Mapper<GiftCertificateCreateDto, GiftCertificate> {
    @Override
    public GiftCertificate mapFrom(GiftCertificateCreateDto obj) {
        return aGiftCertificate()
                .name(obj.name())
                .description(obj.description())
                .price(obj.price())
                .duration(obj.duration())
                .createDate(now())
                .lastUpdateDate(now())
                .build();
    }
}
