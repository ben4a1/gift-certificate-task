package ru.clevertec.ecl.dal.dto;

import lombok.Builder;
import lombok.Data;
import ru.clevertec.ecl.dal.entity.Tag;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class GiftCertificateDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Duration duration;
    private List<Tag> tagList;
}
