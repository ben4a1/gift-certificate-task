package ru.clevertec.ecl.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.dal.entity.Tag;
import ru.clevertec.ecl.dal.repository.GiftCertificateRepository;
import ru.clevertec.ecl.dal.repository.GiftCertificateTagRepository;
import ru.clevertec.ecl.dal.repository.TagRepository;
import ru.clevertec.ecl.service.service.impl.GiftCertificateServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class GiftCertificateServiceTest {

    @InjectMocks
    private GiftCertificateServiceImpl giftCertificateService;
    @Mock
    private GiftCertificateRepository giftCertificateRepository;
    @Mock
    private TagRepository tagRepository;
    @Mock
    private GiftCertificateTagRepository giftCertificateTagRepository;
    @Captor
    private ArgumentCaptor<String> queryCaptor;

    @Test
    void checkFindAllWithoutFilterShouldReturn4() {
        Map<String, String> filterParams = new HashMap<>();
        Mockito.when(giftCertificateRepository.findAll()).thenReturn(getCertificates());
        List<GiftCertificate> resultList = giftCertificateService.findAll(filterParams);
        assertThat(resultList).hasSize(4);
    }

    @Test
    void checkCreateWithoutTagsShouldReturnEquals(){
        GiftCertificate certificateExpected = getCertificates().get(0);
        Mockito.when(giftCertificateRepository.create(certificateExpected)).thenReturn(certificateExpected);
        GiftCertificate giftCertificateActual = giftCertificateService.create(certificateExpected);
        Mockito.verify(giftCertificateRepository).create(any());
        assertThat(giftCertificateActual).isEqualTo(certificateExpected);
    }

    @Test
    void checkCreateWithTagsShouldReturnEquals(){
        GiftCertificate giftCertificateExpected = getCertificates().get(0);
        giftCertificateExpected.setTagList(getTags());
        Mockito.when(giftCertificateRepository.create(giftCertificateExpected)).thenReturn(giftCertificateExpected);
        Mockito.when(tagRepository.create(getTags().get(0))).thenReturn(getTags().get(0));
        GiftCertificate giftCertificateActual = giftCertificateService.create(giftCertificateExpected);
        Mockito.verify(tagRepository, Mockito.times(2)).create(any());
        Mockito.verify(giftCertificateTagRepository, Mockito.times(2)).addDependency(any(), any());
        assertThat(giftCertificateActual).isEqualTo(giftCertificateExpected);
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
