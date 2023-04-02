package ru.clevertec.ecl.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder(builderMethodName = "aTag", toBuilder = true)
public class Tag extends BaseEntity{

    private String name;
    private List<GiftCertificate> giftCertificates;
}
