package ru.clevertec.ecl.dal.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Builder(builderMethodName = "aUser", toBuilder = true)
@EqualsAndHashCode(of = "username", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class User extends AuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;

    private String firstname;

    private String lastname;
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<UserGiftCertificate> userGiftCertificates = new ArrayList<>();
}

