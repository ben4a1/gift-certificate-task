package ru.clevertec.ecl.service.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.clevertec.ecl.dal.dto.GiftCertificateDTO;
import ru.clevertec.ecl.dal.entity.GiftCertificate;

import java.util.List;

@Mapper(componentModel = "spring", uses = GiftCertificateMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GiftCertificateListMapper {
    List<GiftCertificate> toEntityList(List<GiftCertificateDTO> dtoList);
    List<GiftCertificateDTO> toDtoList(List<GiftCertificate> tagList);
}
