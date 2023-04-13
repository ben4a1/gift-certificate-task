package ru.clevertec.ecl.dal.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(of = {"name"}, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(builderMethodName = "aTag", toBuilder = true)
@Entity
@Table(name = "tag")
public class Tag implements BaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @ToString.Exclude
    @Builder.Default
    @ManyToMany(mappedBy = "tagList")
    private List<GiftCertificate> giftCertificates = new ArrayList<>();
}
