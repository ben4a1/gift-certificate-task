package ru.clevertec.ecl.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.ecl.service.dto.GiftCertificateCreateDto;
import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.service.dto.GiftCertificateReadDto;
import ru.clevertec.ecl.service.service.GiftCertificateService;
import ru.clevertec.ecl.web.response.PageResponse;
import ru.clevertec.ecl.service.dto.GiftCertificateFilter;


@RestController
@RequestMapping("/api/v1/certificates")
@RequiredArgsConstructor
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<GiftCertificateReadDto> findAll(GiftCertificateFilter filter, Pageable pageable){

        return null;
    }
}
