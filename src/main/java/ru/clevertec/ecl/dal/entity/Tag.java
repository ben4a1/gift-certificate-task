package ru.clevertec.ecl.dal.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(of = {"name"}, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder(builderMethodName = "aTag", toBuilder = true)
@Entity
@Table(name = "tag")
@AttributeOverride(name = "id", column = @Column(name = "tag_id"))
public class Tag extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;
    @ToString.Exclude
    @Builder.Default
    @ManyToMany(mappedBy = "tagList")
    private List<GiftCertificate> giftCertificates = new ArrayList<>();
}
