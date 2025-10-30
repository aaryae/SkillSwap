package org.example.authservice.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.authservice.model.User;
import org.example.authservice.repository.mapper.UserRowMapper;
import org.example.authservice.repository.sql.UserSql;
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
@RequiredArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public String save(User user) {
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection->{
                PreparedStatement ps = connection.prepareStatement(UserSql.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, user.getEmail());
                        ps.setString(2, user.getPassword());
                        ps.setString(3, user.getUsername());
                        ps.setString(4, user.getBio());
                        ps.setString(5, user.getSkillsOffered());
                        ps.setString(6, user.getSkillWanted());
                        ps.setString(7, user.getRole().toString());
                        ps.setString(8, user.getProfileImage());
                        ps.setString(9, String.valueOf(user.getStatus()));
                return ps;
                    }
                    , keyHolder);
            return keyHolder.getKey().toString();
        }
        catch (DataAccessException e){
            log.error(e.getMessage(),e);
            throw e;
        }


    }

    public Optional<User> findByEmail(String email) {
        try {
            return jdbcTemplate.query(UserSql.FIND_BY_EMAIL, new Object[]{email}, new UserRowMapper())
                    .stream()
                    .findFirst();
        } catch (DataAccessException e) {
            log.error("Error finding user by email: {}", e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<User> findById(String Id) {
        try{
            return jdbcTemplate.query(UserSql.FIND_BY_ID, new Object[]{Id}, new UserRowMapper())
                    .stream()
                    .findFirst();
        }
        catch(DataAccessException e){
            log.error("Error finding user by ID: {}", e.getMessage());
            return Optional.empty();
        }
    }

    public int update(User user) {
        try {
            return jdbcTemplate.update(UserSql.UPDATE_USER,
                    user.getUsername(),
                    user.getBio(),
                    user.getSkillsOffered(),
                    user.getSkillWanted(),
                    user.getProfileImage(),
                    user.getId());
        } catch (DataAccessException e) {
            log.error("Error updating user {}: {}", user.getId(), e.getMessage());
            return 0;
        }
    }

    public boolean deleteById(String id) {
        try {
            return jdbcTemplate.update(UserSql.DELETE_USER, id) > 0;
        } catch (DataAccessException e) {
            log.error("Error deleting user {}: {}", id, e.getMessage());
            return false;
        }
    }
}
