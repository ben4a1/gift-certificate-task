package ru.clevertec.ecl.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.ecl.dal.dto.GiftCertificateDto;
import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.dal.entity.Tag;
import ru.clevertec.ecl.dal.dao.GiftCertificateRepository;
import ru.clevertec.ecl.dal.dao.GiftCertificateTagRepository;
import ru.clevertec.ecl.dal.dao.TagRepository;
import ru.clevertec.ecl.service.mapper.GiftCertificateMapper;
import ru.clevertec.ecl.service.service.impl.GiftCertificateServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class GiftCertificateServiceTest {

//    @InjectMocks
//    private GiftCertificateServiceImpl giftCertificateService;
//    @Mock
//    private GiftCertificateRepository giftCertificateRepository;
//    @Mock
//    private TagRepository tagRepository;
//    @Mock
//    GiftCertificateTagRepository giftCertificateTagRepository;
//
//    @Test
//    void checkFindAllWithoutFilterShouldReturn4() {
//        when(giftCertificateRepository.findAll()).thenReturn(getCertificates());
//        List<GiftCertificateDto> resultList = giftCertificateService.findAll();
//        assertThat(resultList).hasSize(4);
//    }
//
//    @Test
//    void checkCreateWithoutTagsShouldReturnEquals() {
//        GiftCertificateMapper certificateMapper = GiftCertificateMapper.INSTANCE;
//        GiftCertificate giftCertificateExpected = getCertificates().get(0);
//        GiftCertificateDto giftCertificateDto = certificateMapper.toCertificateDTO(giftCertificateExpected);
//        when(giftCertificateRepository.create(giftCertificateExpected)).thenReturn(giftCertificateExpected);
//        GiftCertificateDto giftCertificateActual = giftCertificateService.create(giftCertificateExpected);
//        verify(giftCertificateRepository).create(any());
//        assertThat(giftCertificateActual).isEqualTo(giftCertificateDto);
//    }
//
//    @Test
//    void checkCreateWithTagsShouldReturnEquals(){
//        GiftCertificateMapper certificateMapper = GiftCertificateMapper.INSTANCE;
//        GiftCertificate giftCertificateExpected = getCertificates().get(0);
//        GiftCertificateDto giftCertificateDto = certificateMapper.toCertificateDTO(giftCertificateExpected);
//        giftCertificateExpected.setTagList(getTags());
//        Mockito.when(giftCertificateRepository.create(giftCertificateExpected)).thenReturn(giftCertificateExpected);
//        Mockito.when(tagRepository.create(getTags().get(0))).thenReturn(getTags().get(0));
//        GiftCertificateDto giftCertificateActual = giftCertificateService.create(giftCertificateExpected);
//        Mockito.verify(tagRepository, Mockito.times(2)).create(any());
//        Mockito.verify(giftCertificateTagRepository, Mockito.times(2)).addDependency(any(), any());
//        assertThat(giftCertificateActual).isEqualTo(giftCertificateDto);
//    }
//
//    private List<GiftCertificate> getCertificates() {
//        List<GiftCertificate> certificates = new ArrayList<>();
//        certificates.add(GiftCertificate.aGiftCertificate()
//                .id(1L)
//                .name("Certificate1")
//                .build());
//        certificates.add(GiftCertificate.aGiftCertificate()
//                .id(2L)
//                .name("Certificate2")
//                .build());
//        certificates.add(GiftCertificate.aGiftCertificate()
//                .id(3L)
//                .name("Certificate3")
//                .build());
//        certificates.add(GiftCertificate.aGiftCertificate()
//                .id(4L)
//                .name("Certificate4")
//                .build());
//        return certificates;
//    }
//
//    private List<Tag> getTags() {
//        List<Tag> tags = new ArrayList<>();
//        tags.add(Tag.aTag()
//                .id(1L)
//                .name("Tag1")
//                .build());
//        tags.add(Tag.aTag()
//                .id(2L)
//                .name("Tag2")
//                .build());
//        return tags;
//    }
}
