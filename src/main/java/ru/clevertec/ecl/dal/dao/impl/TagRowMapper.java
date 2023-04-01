package ru.clevertec.ecl.dal.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.clevertec.ecl.dal.entity.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TagRowMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Tag.aTag()
                .id(rs.getLong("tag_id"))
                .name(rs.getString("name"))
                .build();
    }
}
