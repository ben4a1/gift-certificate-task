package ru.clevertec.ecl.service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

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
