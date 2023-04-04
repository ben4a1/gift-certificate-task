package ru.clevertec.ecl.service.dto;

import java.math.BigDecimal;
import java.time.Duration;

public record GiftCertificateCreateDto(String name,
                                       String description,
                                       BigDecimal price,
                                       Duration duration) {
}
