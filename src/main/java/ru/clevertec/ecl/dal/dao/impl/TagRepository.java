package ru.clevertec.ecl.dal.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.clevertec.ecl.dal.entity.Tag;

import javax.persistence.EntityManager;
import java.util.List;

public class TagRepository extends RepositoryBase<Tag, Long> {

    public TagRepository(EntityManager entityManager) {
        super(Tag.class, entityManager);
    }

    Tag findTagByName(String name) {
        return null;
    }

    List<Tag> findAllTagByCertificateId(Long giftCertificateId) {
        return null;
    }

    boolean isTagExists(Tag tag) {
        return false;
    }

}

