package ru.clevertec.ecl.dal.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder(builderMethodName = "aGiftCertificate", toBuilder = true)
@Entity
@Table(name = "gift_certificate")
@AttributeOverride(name = "id", column = @Column(name = "gift_certificate_id"))
public class GiftCertificate extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Duration duration;
    @ToString.Exclude
    @Builder.Default
    @ManyToMany
    @JoinTable()
    private List<Tag> tagList = new ArrayList<>();
}
