package org.example.userservice.repository.mapper;

import org.example.userservice.model.Profile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileMapper implements RowMapper<Profile> {
    @Override
    public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
        Profile profile = new Profile();
        profile.setId(rs.getString("id"));
        profile.setProfileImageUrl(rs.getString("profile_image_url"));
        profile.setEmail(rs.getString("email"));
        profile.setBio(rs.getString("bio"));
        profile.setSkillsOffered(rs.getString("skills_offered"));
        profile.setSkillsWanted(rs.getString("skills_wanted"));
        profile.setProfileImageUrl(rs.getString("profile_image_url"));
        return profile;


    }
}
