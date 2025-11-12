package org.example.skillswap.repository.mapper;

import org.example.skillswap.model.Service;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class ServiceRowMapper  implements RowMapper<Service> {


    @Override
    public Service mapRow(ResultSet rs, int rowNum) throws SQLException {
        Service service =  new Service();
        service.setServiceId(rs.getObject("id", UUID.class));
        service.setCategory(rs.getString("category"));
        service.setDescription(rs.getString("description"));

        return service;

    }
}
