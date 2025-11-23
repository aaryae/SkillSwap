package org.example.skillswap.repository;

import lombok.RequiredArgsConstructor;
import org.example.skillswap.model.Skill;
import org.example.skillswap.repository.mapper.SkillRowMapper;
import org.example.skillswap.repository.sql.SkillSql;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class SkillRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SkillRowMapper rowMapper;

    public int save(Skill service) {
        return jdbcTemplate.update(SkillSql.INSERT_SKILL,
                UUID.randomUUID(),
                service.getTitle(),
                service.getDescription(),
                service.getCategory(),
                service.getPrice(),
                service.getProviderEmail());
    }

    public List<Skill> findAll() {
        return jdbcTemplate.query(SkillSql.SELECT_ALL, rowMapper);
    }

    public Optional<Skill> findById(UUID id) {
        return jdbcTemplate.query(SkillSql.SELECT_BY_ID, rowMapper, id)
                .stream()
                .findFirst();
    }

    public List<Skill> findByProviderEmail(String email) {
        return jdbcTemplate.query(SkillSql.SELECT_BY_PROVIDER_EMAIL, rowMapper, email);
    }

    public int update(Skill service) {
        return jdbcTemplate.update(SkillSql.UPDATE_SKILL,
                service.getTitle(),
                service.getDescription(),
                service.getCategory(),
                service.getPrice(),
                service.getSkillId());
    }

    public int deleteById(UUID id) {
        return jdbcTemplate.update(SkillSql.DELETE_SKILL, id);
    }
}