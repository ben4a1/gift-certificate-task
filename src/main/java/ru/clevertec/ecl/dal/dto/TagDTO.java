package ru.clevertec.ecl.dal.dto;

import lombok.Data;
import ru.clevertec.ecl.dal.entity.GiftCertificate;

import java.util.List;

@Data
public class TagDTO {

    private Long id;
    private String name;
    private List<GiftCertificate> giftCertificates;
}
