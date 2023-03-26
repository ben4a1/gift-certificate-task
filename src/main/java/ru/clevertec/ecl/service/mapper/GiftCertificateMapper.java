package ru.clevertec.ecl.service.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.ecl.dal.dto.GiftCertificateDTO;
import ru.clevertec.ecl.dal.entity.GiftCertificate;

@Mapper(componentModel = "spring", uses = TagListMapper.class)
public interface GiftCertificateMapper {

    GiftCertificate toCertificate(GiftCertificateDTO giftCertificateDTO);
    GiftCertificateDTO toCertificateDTO(GiftCertificate giftCertificate);
}
