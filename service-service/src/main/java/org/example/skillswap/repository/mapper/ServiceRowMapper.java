package org.example.skillswap.repository.mapper;

import org.example.skillswap.model.Service;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class ServiceRowMapper implements RowMapper<Service> {

    @Override
    public Service mapRow(ResultSet rs, int rowNum) throws SQLException {
        Service service = new Service();
        service.setProfileId((rs.getObject("profile_id", UUID.class)));
        service.setTitle(rs.getString("title"));
        service.setDescription(rs.getString("description"));
        service.setCategory(rs.getString("category"));
        service.setProviderEmail(rs.getString("provider_email"));
        service.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        service.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return service;
    }
}
