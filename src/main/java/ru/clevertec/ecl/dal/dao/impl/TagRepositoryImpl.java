package ru.clevertec.ecl.dal.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.clevertec.ecl.dal.entity.Tag;
import ru.clevertec.ecl.dal.dao.TagRepository;
import ru.clevertec.ecl.exception.TagAlreadyExistsException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TagRepositoryImpl implements TagRepository {

    private final String selectAllQuery = "SELECT * FROM tag";
    private final String findByIdQuery = "SELECT * FROM tag WHERE tag_id=:id";
    private final String createQuery = "INSERT INTO tag(name) VALUES (:name)";
    private final String deleteQuery = "DELETE FROM tag WHERE tag_id=:id";
    private final String updateQuery = "UPDATE tag SET name=:name WHERE tag_id=:id";
    private final String findByNameQuery = "SELECT * FROM tag WHERE name=:name";
    private final String getAllTagsByCertificateIdQuery = "SELECT t.tag_id, t.name FROM tag t " +
                                                    "INNER JOIN certificate_tag ct ON t.tag_id = ct.tag_id " +
                                                    "INNER JOIN gift_certificate g ON ct.gift_certificate_id = g.gift_certificate_id " +
                                                    "WHERE g.gift_certificate_id = :giftCertificateId";
    private String tagCountByNameQuery = "SELECT count(*) FROM tag " +
                                       "WHERE name=:name";
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final TagRowMapper tagRowMapper;

    @Autowired
    public TagRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, TagRowMapper tagRowMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.tagRowMapper = tagRowMapper;
    }

    @Override
    public Optional<Tag> findById(Long id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("tag_id", id);
        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(findByIdQuery, sqlParameterSource, tagRowMapper));
    }

    @Override
    public Optional<Tag> findById(Long id, Map<String, Object> properties) {
        return Optional.empty();
    }

    @Override
    public List<Tag> findAll() {
        return namedParameterJdbcTemplate.query(selectAllQuery, tagRowMapper);
    }

    @Override
    public Tag create(Tag object) {
        if (isTagExists(object)){
            throw new TagAlreadyExistsException(object.getName());
        }
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("name", object.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(createQuery, sqlParameterSource, keyHolder, new String[]{"id"});
        return findById((Long) keyHolder.getKey()).get();
    }

    @Override
    public Tag update(Tag object) {
        Long objectId = object.getId();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("tag_id", object.getId());
        sqlParameterSource.addValue("name", object.getName());
        namedParameterJdbcTemplate.update(updateQuery, sqlParameterSource);
        return findById(objectId).get();
    }

    @Override
    public void delete(Long id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("tag_id", id);
        namedParameterJdbcTemplate.update(deleteQuery, sqlParameterSource);
    }

    @Override
    public Tag findTagByName(String name) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("name", name);
        return namedParameterJdbcTemplate.queryForObject(findByNameQuery, sqlParameterSource, tagRowMapper);
    }

    @Override
    public List<Tag> findAllTagByCertificateId(Long giftCertificateId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("giftCertificateId", giftCertificateId);
        return namedParameterJdbcTemplate.query(getAllTagsByCertificateIdQuery, mapSqlParameterSource, tagRowMapper);
    }

    @Override
    public boolean isTagExists(Tag tag) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("name", tag.getName());
        Integer count = namedParameterJdbcTemplate.queryForObject(tagCountByNameQuery, sqlParameterSource, Integer.class);
        return count > 0;
    }
}
