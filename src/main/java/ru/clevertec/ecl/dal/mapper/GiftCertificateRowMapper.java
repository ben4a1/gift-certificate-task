package ru.clevertec.ecl.dal.mapper;

import org.postgresql.util.PGInterval;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.clevertec.ecl.dal.entity.GiftCertificate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;

@Component
public class GiftCertificateRowMapper implements RowMapper<GiftCertificate> {
    @Override
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        return GiftCertificate.aGiftCertificate()
                .id(rs.getLong("gift_certificate_id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .price(rs.getBigDecimal("price"))
                .duration(Duration.ofDays(new PGInterval(rs.getString("duration")).getDays()))
                .createDate(rs.getTimestamp("create_date").toLocalDateTime())
                .lastUpdateDate(rs.getTimestamp("last_update_date").toLocalDateTime())
                .build();
    }
}
