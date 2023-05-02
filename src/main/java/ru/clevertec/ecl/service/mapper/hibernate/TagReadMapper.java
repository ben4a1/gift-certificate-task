package ru.clevertec.ecl.service.mapper.hibernate;

import ru.clevertec.ecl.dal.entity.Tag;
import ru.clevertec.ecl.service.dto.TagReadDto;
import ru.clevertec.ecl.service.mapper.Mapper;

public class TagReadMapper implements Mapper<Tag, TagReadDto> {

    @Override
    public TagReadDto mapFrom(Tag obj) {
        return new TagReadDto(obj.getName());
    }
}
