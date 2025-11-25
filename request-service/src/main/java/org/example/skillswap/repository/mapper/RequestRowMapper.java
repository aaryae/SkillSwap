package org.example.skillswap.repository.mapper;

import org.example.skillswap.dto.response.RequestResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RequestRowMapper implements RowMapper<RequestResponse> {

    @Override
    public RequestResponse mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new RequestResponse(
                rs.getObject("request_id", UUID.class),
                rs.getObject("skill_id", UUID.class),
                rs.getString("requester_email"),
                rs.getString("provider_email"),
                rs.getString("message"),
                rs.getString("status")
        );
    }
}
