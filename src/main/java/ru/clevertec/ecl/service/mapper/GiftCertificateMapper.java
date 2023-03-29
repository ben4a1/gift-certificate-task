package ru.clevertec.ecl.service.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.ecl.dal.dto.GiftCertificateDto;
import ru.clevertec.ecl.dal.entity.GiftCertificate;

@Mapper(componentModel = "spring", uses = TagListMapper.class)
public interface GiftCertificateMapper {

    GiftCertificate toCertificate(GiftCertificateDto giftCertificateDTO);
    GiftCertificateDto toCertificateDTO(GiftCertificate giftCertificate);
}
