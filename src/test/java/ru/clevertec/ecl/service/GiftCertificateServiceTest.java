package ru.clevertec.ecl.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.dal.repository.GiftCertificateRepository;
import ru.clevertec.ecl.dal.repository.TagRepository;
import ru.clevertec.ecl.service.service.impl.GiftCertificateServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class GiftCertificateServiceTest {

    @InjectMocks
    private GiftCertificateServiceImpl giftCertificateService;
    @Mock
    private GiftCertificateRepository giftCertificateRepository;
    @Mock
    private TagRepository tagRepository;
    @Captor
    private ArgumentCaptor<String> queryCaptor;

    @Test
    void checkFindAllWithoutFilterShouldReturn4() {
        Map<String, String> filterParams = new HashMap<>();
        Mockito.when(giftCertificateRepository.findAll()).thenReturn(getCertificates());
        List<GiftCertificate> resultList = giftCertificateService.findAll(filterParams);
        assertThat(resultList).hasSize(4);
    }

    private List<GiftCertificate> getCertificates() {
        List<GiftCertificate> certificates = new ArrayList<>();
        certificates.add(GiftCertificate.aGiftCertificate()
                .id(1L)
                .name("Certificate1")
                .build());
        certificates.add(GiftCertificate.aGiftCertificate()
                .id(2L)
                .name("Certificate2")
                .build());
        certificates.add(GiftCertificate.aGiftCertificate()
                .id(3L)
                .name("Certificate3")
                .build());
        certificates.add(GiftCertificate.aGiftCertificate()
                .id(4L)
                .name("Certificate4")
                .build());
        return certificates;
    }
}
