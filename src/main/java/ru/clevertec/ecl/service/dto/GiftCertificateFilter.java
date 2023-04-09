package ru.clevertec.ecl.service.dto;

import java.math.BigDecimal;

public record GiftCertificateFilter(String name,
                                    BigDecimal price) {
}
