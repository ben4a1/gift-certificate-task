package ru.clevertec.ecl.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.ecl.dal.entity.Tag;
import ru.clevertec.ecl.service.service.TagService;

import java.util.List;

@RestController
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping(value="/tags", produces = "application/json")
    public ResponseEntity<List<Tag>> tags() {

        List<Tag> tags = tagService.findAll();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @PostMapping(value="/tags", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {

        Tag newTag = tagService.create(tag);
        return new ResponseEntity<>(newTag, HttpStatus.OK);
    }

    @PutMapping(value = "/tags/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestBody Tag tag) {
        tag.setId(id);
        Tag updateTag = tagService.update(tag);
        return new ResponseEntity<>(updateTag, HttpStatus.OK);
    }

    @DeleteMapping(value = "/tags/{id}", produces = {"application/json"})
    public ResponseEntity<Boolean> deleteTag(@PathVariable Long id) {
        tagService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
