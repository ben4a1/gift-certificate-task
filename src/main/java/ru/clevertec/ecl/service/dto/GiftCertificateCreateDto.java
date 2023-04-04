package ru.clevertec.ecl.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Duration;

public record GiftCertificateCreateDto(
        @NotNull
        @Size(min = 2, max = 100)
        String name,
        @NotNull
        @Size(min = 10, max = 200)
        String description,
        @NotNull
        @Positive
        BigDecimal price,
        Duration duration) {
}
