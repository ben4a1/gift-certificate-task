package ru.clevertec.ecl.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder(builderMethodName = "aTag", toBuilder = true)
@Entity
@Table(name = "tag")
@AttributeOverride(name = "id", column = @Column(name = "tag_id"))
public class Tag extends BaseEntity{

    private String name;
    private List<GiftCertificate> giftCertificates;
}
