package ru.clevertec.ecl.service.mapper.hibernate;

import ru.clevertec.ecl.dal.entity.Tag;
import ru.clevertec.ecl.service.dto.TagCreateDto;
import ru.clevertec.ecl.service.mapper.Mapper;

import static ru.clevertec.ecl.dal.entity.Tag.*;

public class TagCreateMapper implements Mapper<TagCreateDto, Tag> {

    @Override
    public Tag mapFrom(TagCreateDto obj) {
        return aTag()
                .name(obj.name())
                .build();
    }
}
