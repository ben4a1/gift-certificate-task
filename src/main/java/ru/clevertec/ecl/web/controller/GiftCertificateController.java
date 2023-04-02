package ru.clevertec.ecl.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.ecl.dal.dto.GiftCertificateDto;
import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.service.service.GiftCertificateService;

import java.util.List;
import java.util.Map;

@RestController
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }
    @GetMapping(value="/certificates", produces = "application/json")
    public ResponseEntity<List<GiftCertificateDto>> certificates(@RequestParam(required=false) Map<String,String> filterParams) {
        List<GiftCertificateDto> giftCertificates = giftCertificateService.findAll();
        return new ResponseEntity<>(giftCertificates, HttpStatus.OK);
    }

    @GetMapping(value="/certificates/{id}", produces = "application/json")
    public ResponseEntity<GiftCertificateDto> certificateById(@PathVariable Long id) {
        GiftCertificateDto giftCertificate = giftCertificateService.findById(id);
        return new ResponseEntity<>(giftCertificate, HttpStatus.OK);
    }

    @PostMapping(value="/certificates", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GiftCertificateDto> createCertificate(@RequestBody GiftCertificate certificate) {
        GiftCertificateDto giftCertificate = giftCertificateService.create(certificate);
        return new ResponseEntity<>(giftCertificate, HttpStatus.CREATED);
    }

    @PutMapping(value = "/certificates/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<GiftCertificateDto> updateCertificate(@PathVariable Long id, @RequestBody GiftCertificate certificate) {
        certificate.setId(id);
        GiftCertificateDto update = giftCertificateService.update(certificate);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping(value = "/certificates/{id}", produces = {"application/json"})
    public ResponseEntity<Void> deleteCertificate(@PathVariable Long id) {
        giftCertificateService.delete(id);
        return ResponseEntity.ok().build();
    }
}
