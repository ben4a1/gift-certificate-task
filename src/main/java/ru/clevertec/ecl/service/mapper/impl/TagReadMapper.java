package ru.clevertec.ecl.service.mapper.impl;

import ru.clevertec.ecl.dal.entity.Tag;
import ru.clevertec.ecl.service.dto.TagReadDto;
import ru.clevertec.ecl.service.mapper.Mapper;

public class TagReadMapper implements Mapper<Tag, TagReadDto> {

    @Override
    public TagReadDto map(Tag object) {
        return new TagReadDto(object.getName()
        );
    }
}
