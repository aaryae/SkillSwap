package org.example.skillswap.repository.mapper;

import org.example.skillswap.model.Skill;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class SkillRowMapper implements RowMapper<Skill> {

    @Override
    public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
        Skill service = new Skill();
        service.setSkillId((rs.getObject("profile_id", UUID.class)));
        service.setTitle(rs.getString("title"));
        service.setDescription(rs.getString("description"));
        service.setCategory(rs.getString("category"));
        service.setProviderEmail(rs.getString("provider_email"));
        service.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        service.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return service;
    }
}
