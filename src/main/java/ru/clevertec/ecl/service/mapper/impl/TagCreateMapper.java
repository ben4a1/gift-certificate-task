package ru.clevertec.ecl.service.mapper.impl;

import ru.clevertec.ecl.dal.entity.Tag;
import ru.clevertec.ecl.service.dto.TagCreateDto;
import ru.clevertec.ecl.service.mapper.Mapper;

import static ru.clevertec.ecl.dal.entity.Tag.*;

public class TagCreateMapper implements Mapper<TagCreateDto, Tag> {
    @Override
    public Tag map(TagCreateDto object) {
        return aTag()
                .name(object.name())
                .build();
    }
}
