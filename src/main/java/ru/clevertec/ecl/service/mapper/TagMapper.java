package ru.clevertec.ecl.service.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.ecl.dal.dto.TagDto;
import ru.clevertec.ecl.dal.entity.Tag;

@Mapper(componentModel = "spring", uses = GiftCertificateListMapper.class)
public interface TagMapper {

    Tag toTag(TagDto tagDto);
    TagDto toTagDto(Tag tag);
}
