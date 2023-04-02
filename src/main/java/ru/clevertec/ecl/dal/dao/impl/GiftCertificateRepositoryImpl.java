package ru.clevertec.ecl.dal.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.dal.dao.GiftCertificateRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class GiftCertificateRepositoryImpl implements GiftCertificateRepository {

    private final String selectAllQuery = "SELECT * FROM gift_certificate";
    private final String createQuery = "INSERT INTO gift_certificate(name, description, price, create_date, last_update_date, duration)" +
                                       "VALUES (:name, :description, :price, now(), now(), :duration)";
    private final String findByIdQuery = "SELECT * FROM gift_certificate WHERE gift_certificate_id=:id";
    private final String deleteByIdQuery = "DELETE FROM gift_certificate WHERE gift_certificate_id=:id";
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final GiftCertificateRowMapper giftCertificateRowMapper;

    @Autowired
    public GiftCertificateRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, GiftCertificateRowMapper giftCertificateRowMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.giftCertificateRowMapper = giftCertificateRowMapper;
    }

    @Override
    public Optional<GiftCertificate> findById(Long id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("gift_certificate_id", id);
        GiftCertificate certificate;
        try {
            certificate = namedParameterJdbcTemplate.queryForObject(findByIdQuery, sqlParameterSource, giftCertificateRowMapper);
        } catch (EmptyResultDataAccessException exception) {
            throw new RuntimeException();
        }
        return Optional.ofNullable(certificate);
    }

    @Override
    public Optional<GiftCertificate> findById(Long id, Map<String, Object> properties) {
        return Optional.empty();
    }

    @Override
    public List<GiftCertificate> findAll() {
        return namedParameterJdbcTemplate.query(selectAllQuery, giftCertificateRowMapper);
    }

    @Override
    public GiftCertificate create(GiftCertificate object) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", object.getName());
        mapSqlParameterSource.addValue("description", object.getDescription());
        mapSqlParameterSource.addValue("price", object.getPrice());
        mapSqlParameterSource.addValue("duration", object.getDuration());
        mapSqlParameterSource.addValue("create_date", object.getCreateDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(createQuery, mapSqlParameterSource, keyHolder, new String[]{"id"});
        return findById((Long) keyHolder.getKey()).get();
    }

    @Override
    public GiftCertificate update(GiftCertificate object) {
        Long objectId = object.getId();
        GiftCertificate existingCertificate = findById(objectId).get();
        StringBuilder updateCertificateQuery = new StringBuilder("UPDATE gift_certificate SET");
        boolean needToUpdate = false;
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("gift_certificate_id", object.getId());
        if (!object.getName().equals(existingCertificate.getName())) {
            needToUpdate = true;
            updateCertificateQuery.append(" name=:name,");
            mapSqlParameterSource.addValue("name", object.getName());
        } else if (!object.getDescription().equals(existingCertificate.getDescription())) {
            needToUpdate = true;
            updateCertificateQuery.append(" description=:description,");
            mapSqlParameterSource.addValue("description", object.getDescription());
        } else if (object.getPrice() != existingCertificate.getPrice()) {
            needToUpdate = true;
            updateCertificateQuery.append(" price=:price,");
            mapSqlParameterSource.addValue("price", object.getPrice());
        } else if (!object.getDuration().equals(existingCertificate.getDuration())) {
            needToUpdate = true;
            updateCertificateQuery.append(" duration=:duration,");
            mapSqlParameterSource.addValue("duration", object.getDuration());
        }
        if (needToUpdate) {
            updateCertificateQuery.append(" last_update_date=:last_update_date,");
            mapSqlParameterSource.addValue("last_update_date", LocalDateTime.now());
            updateCertificateQuery.deleteCharAt(updateCertificateQuery.length() - 1);
            updateCertificateQuery.append(" WHERE id=:id");
            namedParameterJdbcTemplate.update(updateCertificateQuery.toString(), mapSqlParameterSource);
            return findById(objectId).get();
        }
        return existingCertificate;
    }

    @Override
    public void delete(Long id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("gift_certificate_id", id);
        int status = namedParameterJdbcTemplate.update(deleteByIdQuery, sqlParameterSource);
        if (status != 0) {
            System.out.println("Gift certificate data deleted for ID " + id);
        } else {
            System.out.println("No Gift certificate found with ID " + id);
        }
    }


    @Override
    public List<GiftCertificate> findByPartOfName(String partOfName) {
        String query = "SELECT * FROM gift_certificate WHERE name LIKE concat('%',?,'%')";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", partOfName);
        return namedParameterJdbcTemplate.query(query, mapSqlParameterSource, giftCertificateRowMapper);
    }

    @Override
    public List<GiftCertificate> findByPartOfDescription(String partOfDescription) {
        String query = "SELECT * FROM gift_certificate WHERE description LIKE concat('%',?,'%')";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("description", partOfDescription);
        return namedParameterJdbcTemplate.query(query, mapSqlParameterSource, giftCertificateRowMapper);
    }
}
