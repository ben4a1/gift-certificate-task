package ru.clevertec.ecl.service.service.impl;


import ru.clevertec.ecl.dal.dao.impl.TagRepository;
import ru.clevertec.ecl.dal.entity.Tag;

public class TagService extends ServiceBase<Tag, Long>{

    public TagService(TagRepository tagRepository) {
        super(tagRepository);
    }
}
