package org.example.authservice.repository.mapper;

import org.example.authservice.helper.Role;
import org.example.authservice.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getObject("id", UUID.class));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setRole(Role.valueOf(rs.getString("role")));
        user.setCreatedAt(rs.getString("created_at"));
        user.setUpdatedAt(rs.getString("updated_at"));
        return user;
    }
}
