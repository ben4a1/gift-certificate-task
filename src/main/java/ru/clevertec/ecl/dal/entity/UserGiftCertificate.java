package ru.clevertec.ecl.dal.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "users_certificate")
@SuperBuilder
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class UserGiftCertificate implements BaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gift_certificate_id")
    private GiftCertificate certificate;

    public void setUser(User user) {
        this.user = user;
        this.user.getUserGiftCertificates().add(this);
    }

    public void setCertificate(GiftCertificate certificate) {
        this.certificate = certificate;
        this.certificate.getUserGiftCertificates().add(this);
    }
}
