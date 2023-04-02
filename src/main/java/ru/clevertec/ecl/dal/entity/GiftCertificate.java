package ru.clevertec.ecl.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder(builderMethodName = "aGiftCertificate", toBuilder = true)
public class GiftCertificate extends BaseEntity {

    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Duration duration;
    private List<Tag> tagList;
}
