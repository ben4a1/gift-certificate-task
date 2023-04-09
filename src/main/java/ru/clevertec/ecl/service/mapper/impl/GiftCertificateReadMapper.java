package ru.clevertec.ecl.service.mapper.impl;

import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.service.dto.GiftCertificateReadDto;
import ru.clevertec.ecl.service.mapper.Mapper;


public class GiftCertificateReadMapper implements Mapper<GiftCertificate, GiftCertificateReadDto> {
    @Override
    public GiftCertificateReadDto map(GiftCertificate object) {
        return new GiftCertificateReadDto(
                object.getName(),
                object.getDescription(),
                object.getPrice(),
                object.getCreateDate(),
                object.getLastUpdateDate(),
                object.getDuration()
        );
    }
}