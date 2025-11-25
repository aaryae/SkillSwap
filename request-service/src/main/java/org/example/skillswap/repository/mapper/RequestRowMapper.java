package org.example.skillswap.repository.mapper;

import org.example.skillswap.helper.StatusEnum;
import org.example.skillswap.model.Request;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RequestRowMapper implements RowMapper<Request> {
    @Override
    public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
        Request request = new Request();
        request.setRequestId(rs.getObject("requestId",UUID.class));
        request.setRequesterEmail(rs.getString("RequesterEmail"));
        request.setProviderEmail(rs.getString("ProviderEmail"));
        request.setMessage(rs.getString("Message"));
        request.setStatus(StatusEnum.valueOf(rs.getString("status")));
        request.setCreatedAt(rs.getTimestamp("created_at"));
        request.setUpdatedAt(rs.getTimestamp("updated_at"));

        return request;

    }
}
