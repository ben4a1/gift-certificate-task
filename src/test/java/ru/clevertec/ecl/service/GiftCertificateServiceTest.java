package ru.clevertec.ecl.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.ecl.dal.dao.GiftCertificateTagRepository;
import ru.clevertec.ecl.dal.dao.impl.GiftCertificateRepository;
import ru.clevertec.ecl.dal.dao.impl.TagRepository;
import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.dal.entity.Tag;
import ru.clevertec.ecl.service.dto.GiftCertificateReadDto;
import ru.clevertec.ecl.service.mapper.hibernate.GiftCertificateReadMapper;
import ru.clevertec.ecl.service.service.GiftCertificateService;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static ru.clevertec.ecl.dal.entity.GiftCertificate.*;


public class GiftCertificateServiceTest {

    private List<GiftCertificate> getCertificates() {
        List<GiftCertificate> certificates = new ArrayList<>();
        certificates.add(aGiftCertificate()
                .name("cert1")
                .description("description1")
                .price(new BigDecimal(155))
                .createDate(now())
                .lastUpdateDate(now())
                .build());
                certificates.add(aGiftCertificate()
                        .name("cert2")
                        .description("description2")
                        .price(new BigDecimal(1552))
                        .createDate(now())
                        .lastUpdateDate(now())
                        .build());
        certificates.add(aGiftCertificate()
                .name("cert3")
                .description("description3")
                .price(new BigDecimal(1553))
                .createDate(now())
                .lastUpdateDate(now())
                .build());
        certificates.add(aGiftCertificate()
                .name("cert4")
                .description("description4")
                .price(new BigDecimal(1554))
                .createDate(now())
                .lastUpdateDate(now())
                .build());
        return certificates;
    }

    private List<Tag> getTags() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.aTag()
                .id(1L)
                .name("Tag1")
                .build());
        tags.add(Tag.aTag()
                .id(2L)
                .name("Tag2")
                .build());
        return tags;
    }
}
