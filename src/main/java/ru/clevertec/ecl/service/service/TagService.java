package ru.clevertec.ecl.service.service;


import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.ecl.dal.dao.TagRepository;
import ru.clevertec.ecl.dal.entity.Tag;
import ru.clevertec.ecl.service.dto.TagCreateDto;
import ru.clevertec.ecl.service.dto.TagReadDto;
import ru.clevertec.ecl.service.mapper.Mapper;

import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.service.mapper.impl.TagCreateMapper;
import ru.clevertec.ecl.service.mapper.impl.TagReadMapper;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TagService{

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
        Tag tag = tagCreateMapper.map(tagDto);
        return tagRepository.save(tag).getId();
    }

    @Transactional
    public <T> Optional<T> findById(Long id, Mapper<Tag, T> mapper) {
        return tagRepository.findById(id)
                .map(mapper::map);
    }

    @Transactional
    public Optional<TagReadDto> findById(Long id) {
        return findById(id, tagReadMapper);
    }

    @Transactional
    public boolean delete(Long id) {
        var maybeCertificate = tagRepository.findById(id);
        maybeCertificate.ifPresent(tagRepository::delete);
        return maybeCertificate.isPresent();
    }

    public List<TagReadDto> findAll() {
        return tagRepository.findAll().stream().map(tagReadMapper::mapFrom).toList();
    }

    public TagReadDto update(Tag tag) {
        return tagReadMapper.map(tagRepository.save(tag));
    }
}
