package ru.clevertec.ecl.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.service.service.GiftCertificateService;

import java.util.List;
import java.util.Map;

@RestController
public class GiftCertificateController {

    private GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }
    @GetMapping(value="/certificates", produces = "application/json")
    public ResponseEntity<List<GiftCertificate>> certificates(@RequestParam(required=false) Map<String,String> filterParams) {
        List<GiftCertificate> giftCertificates = giftCertificateService.findAll(filterParams);
        return new ResponseEntity<>(giftCertificates, HttpStatus.OK);
    }

    @GetMapping(value="/certificates/{id}", produces = "application/json")
    public ResponseEntity<GiftCertificate> certificateById(@PathVariable Long id) {
        GiftCertificate giftCertificate = giftCertificateService.findById(id);
        return new ResponseEntity<>(giftCertificate, HttpStatus.OK);
    }

    @PostMapping(value="/certificates", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GiftCertificate> createCertificate(@RequestBody GiftCertificate certificate) {
        GiftCertificate giftCertificate = giftCertificateService.create(certificate);
        return new ResponseEntity<>(giftCertificate, HttpStatus.CREATED);
    }

    @PutMapping(value = "/certificates/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<GiftCertificate> updateCertificate(@PathVariable Long id, @RequestBody GiftCertificate certificate) {
        certificate.setId(id);
        GiftCertificate update = giftCertificateService.update(certificate);
        return new ResponseEntity(update, HttpStatus.OK);
    }

    @DeleteMapping(value = "/certificates/{id}", produces = {"application/json"})
    public ResponseEntity<GiftCertificate> deleteCertificate(@PathVariable Long id) {
        GiftCertificate giftCertificate = giftCertificateService.findById(id);
        giftCertificateService.delete(id);
        return new ResponseEntity(giftCertificate, HttpStatus.OK);
    }
}
