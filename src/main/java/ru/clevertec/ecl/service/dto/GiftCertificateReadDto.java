package ru.clevertec.ecl.service.dto;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public record GiftCertificateReadDto(Long id,
                                     String name,
                                     String description,
                                     BigDecimal price,
                                     LocalDateTime createDate,
                                     LocalDateTime lastUpdateDate,
                                     Duration duration) {
}
