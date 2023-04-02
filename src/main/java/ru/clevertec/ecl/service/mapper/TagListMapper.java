package ru.clevertec.ecl.service.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.clevertec.ecl.dal.dto.TagDto;
import ru.clevertec.ecl.dal.entity.Tag;

import java.util.List;

@Mapper(componentModel = "spring", uses = TagMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TagListMapper {
    List<TagDto> toDtoList(List<Tag> tagList);
    List<Tag> toEntityList(List<TagDto> dtoList);
}
