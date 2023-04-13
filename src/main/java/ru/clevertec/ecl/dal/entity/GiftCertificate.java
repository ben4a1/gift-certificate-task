package ru.clevertec.ecl.dal.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(builderMethodName = "aGiftCertificate", toBuilder = true)
@Entity
@Table(name = "gift_certificate")
@ToString(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "gift_certificate_id"))
public class GiftCertificate implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;

    @Column(
            name = "duration",
            columnDefinition = "interval"
    )
    private Duration duration;
    @ToString.Exclude
    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "certificate_tag",
            joinColumns = @JoinColumn(name = "gift_certificate_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tagList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "certificate")
    private List<UserGiftCertificate> userGiftCertificates = new ArrayList<>();
}
