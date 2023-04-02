package ru.clevertec.ecl.dal.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import ru.clevertec.ecl.dal.repository.GiftCertificateTagRepository;

@Component
public class GiftCertificateTagRepositoryImpl implements GiftCertificateTagRepository {

    private final String giftCertificateTagCountQuery = "SELECT count(*) FROM certificates_tags " +
                                            "WHERE gift_certificate_id = :giftCertificateId AND tag_id = :tagId";
    private final String addGiftCertificateDependencyQuery = "INSERT INTO certificates_tags(" +
                                             "gift_certificate_id, tag_id) VALUES (:giftCertificateId, :tagId)";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public GiftCertificateTagRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    @Override
    public boolean isTagExists(Long giftCertificateId, Long tagId) {
        Integer count = namedParameterJdbcTemplate.queryForObject(giftCertificateTagCountQuery, fillMapSqlParameterSource(giftCertificateId, tagId), Integer.class);
        return count > 0;
    }

    @Override
    public void addDependency(Long giftCertificateId, Long tagId) {
        namedParameterJdbcTemplate.update(addGiftCertificateDependencyQuery, fillMapSqlParameterSource(giftCertificateId, tagId));
    }

    private static MapSqlParameterSource fillMapSqlParameterSource(Long giftCertificateId, Long tagId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("gift_certificate_id", giftCertificateId);
        mapSqlParameterSource.addValue("tag_id", tagId);
        return mapSqlParameterSource;
    }
}
