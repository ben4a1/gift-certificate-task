package ru.clevertec.ecl.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.clevertec.ecl.service.dto.TagDto;
import ru.clevertec.ecl.dal.entity.Tag;

@Mapper(componentModel = "spring", uses = GiftCertificateListMapper.class)
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);
    Tag toTag(TagDto tagDto);
    TagDto toTagDto(Tag tag);
}
