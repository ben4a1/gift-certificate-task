package ru.clevertec.ecl.dal.repository;

import ru.clevertec.ecl.dal.entity.Tag;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Long>{

    Tag findTagByName(String name);

    List<Tag> findAllTagByCertificateId(Long giftCertificateId);
    boolean isTagExists(Tag tag);
}
