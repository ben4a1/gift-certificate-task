package ru.clevertec.ecl.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.ecl.service.dto.GiftCertificateCreateDto;
import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.service.dto.GiftCertificateReadDto;
import ru.clevertec.ecl.service.service.GiftCertificateService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/certificates")
@RequiredArgsConstructor
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;

    @GetMapping
    public ResponseEntity<List<GiftCertificateReadDto>> certificates(@RequestParam(required = false) Map<String, String> filterParams) {
        List<GiftCertificateReadDto> giftCertificates = giftCertificateService.findAll();
        return new ResponseEntity<>(giftCertificates, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GiftCertificateReadDto> certificateById(@PathVariable Long id) {
        Optional<GiftCertificateReadDto> certificateReadDto = giftCertificateService.findById(id);
        return new ResponseEntity<>(certificateReadDto.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createCertificate(@RequestBody GiftCertificateCreateDto certificate) {
        Long id = giftCertificateService.create(certificate);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GiftCertificateReadDto> updateCertificate(@PathVariable Long id, @RequestBody GiftCertificate certificate) {
        certificate.setId(id);
        GiftCertificateReadDto giftCertificateReadDto = giftCertificateService.update(certificate);
        return new ResponseEntity<>(giftCertificateReadDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable Long id) {
        giftCertificateService.delete(id);
        return ResponseEntity.ok().build();
    }
}
