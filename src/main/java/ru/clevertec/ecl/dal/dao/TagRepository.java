package ru.clevertec.ecl.dal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.ecl.dal.entity.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

}

