package org.example.userservice.repository.mapper;

import org.example.userservice.model.Profile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ProfileMapper implements RowMapper<Profile> {
    @Override
    public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
        Profile profile = new Profile();
        profile.setId(rs.getObject("profile_id", UUID.class));
        profile.setEmail(rs.getString("email"));
        profile.setBio(rs.getString("bio"));
        profile.setSkillsOffered(rs.getString("skills_offered"));
        profile.setSkillsWanted(rs.getString("skills_wanted"));
        return profile;


    }
}
