package org.example.authservice.repository.mapper;

import org.example.authservice.helper.Role;
import org.example.authservice.helper.UserStatus;
import org.example.authservice.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setBio(rs.getString("bio"));
        user.setSkillsOffered(rs.getString("skills_offered"));
        user.setSkillWanted(rs.getString("skill_wanted"));
        user.setRole(Role.valueOf(rs.getString("role")));
        user.setProfileImage(rs.getString("profile_image"));
        user.setStatus(UserStatus.valueOf(rs.getString("status")));
        user.setCreatedAt(rs.getString("created_at"));
        user.setUpdatedAt(rs.getString("updated_at"));
        return user;
    }
}
