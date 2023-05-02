package ru.clevertec.ecl.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.ecl.dal.dao.impl.TagRepository;
import ru.clevertec.ecl.dal.entity.Tag;
import ru.clevertec.ecl.service.dto.TagCreateDto;
import ru.clevertec.ecl.service.dto.TagReadDto;
import ru.clevertec.ecl.service.mapper.Mapper;
import ru.clevertec.ecl.service.mapper.hibernate.TagCreateMapper;
import ru.clevertec.ecl.service.mapper.hibernate.TagReadMapper;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class TagService {

    private final TagRepository tagRepository;
    private final TagReadMapper tagReadMapper;
    private final TagCreateMapper tagCreateMapper;

    @Transactional
    public Long create(TagCreateDto tagDto) {
        var validatorFactory = Validation.buildDefaultValidatorFactory();
        var validator = validatorFactory.getValidator();
        var validationResult = validator.validate(tagDto);
        if (!validationResult.isEmpty()) {
            throw new ConstraintViolationException(validationResult);
        }
        Tag tag = tagCreateMapper.mapFrom(tagDto);
        return tagRepository.create(tag).getId();
    }

    @Transactional
    public <T> Optional<T> findById(Long id, Mapper<Tag, T> mapper) {
        return tagRepository.findById(id)
                .map(mapper::mapFrom);
    }

    @Transactional
    public Optional<TagReadDto> findById(Long id) {
        return findById(id, tagReadMapper);
    }

    @Transactional
    public boolean delete(Long id) {
        var maybeCertificate = tagRepository.findById(id);
        maybeCertificate.ifPresent(Tag -> tagRepository.delete(Tag.getId()));
        return maybeCertificate.isPresent();
    }

    public List<TagReadDto> findAll() {
        return tagRepository.findAll().stream().map(tagReadMapper::mapFrom).toList();
    }

    public TagReadDto update(Tag tag) {
        return tagReadMapper.mapFrom(tagRepository.update(tag));
    }
}
