package ru.clevertec.ecl.service.dto;

import lombok.Builder;
import lombok.Data;
import ru.clevertec.ecl.dal.entity.GiftCertificate;

import java.util.List;

@Data
@Builder
public class TagDto {

    private Long id;
    private String name;
    private List<GiftCertificate> giftCertificates;
}
