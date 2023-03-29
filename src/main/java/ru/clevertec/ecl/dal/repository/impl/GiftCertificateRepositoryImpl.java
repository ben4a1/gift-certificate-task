package ru.clevertec.ecl.dal.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.dal.repository.GiftCertificateRepository;

import java.time.LocalDateTime;
import java.util.List;

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
    public GiftCertificate findById(Long id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("gift_certificate_id", id);
        GiftCertificate certificate;
        try {
            certificate = namedParameterJdbcTemplate.queryForObject(findByIdQuery, sqlParameterSource, giftCertificateRowMapper);
        } catch (EmptyResultDataAccessException exception){
            throw new RuntimeException();
        }
        return certificate;
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
        return findById((Long) keyHolder.getKey());
    }

    @Override
    public GiftCertificate update(GiftCertificate object) {
        Long objectId = object.getId();
        GiftCertificate existingCertificate = findById(objectId);
        if (existingCertificate == null){
            System.out.println("No Gift certificate found with ID " + objectId);
            return null;
        }
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
            return findById(objectId);
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
    public List<GiftCertificate> findAllWithFilter(String specificRequest) {
        return namedParameterJdbcTemplate.query(specificRequest, giftCertificateRowMapper);
    }
}
