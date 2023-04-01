package ru.clevertec.ecl.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.dal.entity.Tag;
import ru.clevertec.ecl.dal.dao.TagRepository;
import ru.clevertec.ecl.dal.dao.impl.TagRepositoryImpl;
import ru.clevertec.ecl.service.service.TagService;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepositoryImpl tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag create(Tag tag) {
        return tagRepository.create(tag);
    }

    @Override
    public Tag update(Tag tag) {
        return tagRepository.update(tag);
    }

    @Override
    public void delete(Long id) {
        tagRepository.delete(id);
    }
}
