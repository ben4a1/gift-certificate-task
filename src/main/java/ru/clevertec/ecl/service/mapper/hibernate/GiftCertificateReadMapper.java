package ru.clevertec.ecl.service.mapper.hibernate;

import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.service.dto.GiftCertificateReadDto;
import ru.clevertec.ecl.service.mapper.Mapper;

public class GiftCertificateReadMapper implements Mapper<GiftCertificate, GiftCertificateReadDto> {

    @Override
    public GiftCertificateReadDto mapFrom(GiftCertificate obj) {
        return new GiftCertificateReadDto(
                obj.getName(),
                obj.getDescription(),
                obj.getPrice(),
                obj.getCreateDate(),
                obj.getLastUpdateDate(),
                obj.getDuration());
    }
}
