package ru.clevertec.ecl.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.ecl.dal.entity.Tag;
import ru.clevertec.ecl.service.dto.TagCreateDto;
import ru.clevertec.ecl.service.dto.TagReadDto;
import ru.clevertec.ecl.service.service.TagService;

import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<TagReadDto>> tags() {

        List<TagReadDto> tagReadDtos = tagService.findAll();
        return new ResponseEntity<>(tagReadDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createTag(@RequestBody TagCreateDto tagCreateDto) {

        Long id = tagService.create(tagCreateDto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TagReadDto> updateTag(@PathVariable Long id, @RequestBody Tag tag) {
        tag.setId(id);
        TagReadDto tagReadDto = tagService.update(tag);
        return new ResponseEntity<>(tagReadDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteTag(@PathVariable Long id) {
        tagService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
