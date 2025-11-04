package org.example.userservice.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.userservice.model.Profile;
import org.example.userservice.repository.mapper.ProfileMapper;
import org.example.userservice.repository.sql.ProfileSql;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

@Slf4j
@Repository
@AllArgsConstructor
public class ProfileRepository {
    private final JdbcTemplate jdbcTemplate;

    public String save(Profile profile) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection-> {
                PreparedStatement ps = connection.prepareStatement(ProfileSql.SAVE_PROFILE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, profile.getEmail());
                ps.setString(2,profile.getUserName());
                ps.setString (3, profile.getFullName());
                ps.setString(4, profile.getBio());
                ps.setString(5, profile.getSkillsOffered());
                ps.setString(6, profile.getSkillsWanted());
                ps.setString(8, profile.getProfileImageUrl());
                return ps;
            },keyHolder);
            return keyHolder.getKey().toString();
            }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Optional<Profile> findByEmail(String email) {
        try {
            return jdbcTemplate.query(ProfileSql.GET_USER_PROFILE_BY_EMAIL, new Object[]{email}, new ProfileMapper())
                    .stream()
                    .findFirst();
        } catch (DataAccessException e) {
            log.error("Error finding user by email: {}", e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Profile> findById(String id) {
        try{
            return jdbcTemplate.query(ProfileSql.GET_USER_PROFILE_BY_ID,new Object[]{id}, new ProfileMapper())
                    .stream()
                    .findFirst();
        } catch (DataAccessException e) {
            log.error("Error finding user by id: {}", e.getMessage());
            return Optional.empty();
        }
        }

        public Optional<Profile> findAllProfile(){
        try{
            return jdbcTemplate.query(ProfileSql.GET_USER_PROFILE,new ProfileMapper())
                    .stream()
                    .findFirst();
        }catch (DataAccessException e) {
            log.error( e.getMessage());
            return Optional.empty();
        }
        }

}
