package org.example.skillswap.repository;

import lombok.RequiredArgsConstructor;
import org.example.skillswap.model.Service;
import org.example.skillswap.repository.mapper.ServiceRowMapper;
import org.example.skillswap.repository.sql.ServiceSql;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class ServiceRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ServiceRowMapper rowMapper;

    public int save(Service service) {
        return jdbcTemplate.update(ServiceSql.INSERT_SERVICE,
                UUID.randomUUID(),
                service.getTitle(),
                service.getDescription(),
                service.getCategory(),
                service.getPrice(),
                service.getProviderEmail());
    }

    public List<Service> findAll() {
        return jdbcTemplate.query(ServiceSql.SELECT_ALL, rowMapper);
    }

    public Optional<Service> findById(UUID id) {
        return jdbcTemplate.query(ServiceSql.SELECT_BY_ID, rowMapper, id)
                .stream()
                .findFirst();
    }

    public List<Service> findByProviderEmail(String email) {
        return jdbcTemplate.query(ServiceSql.SELECT_BY_PROVIDER_EMAIL, rowMapper, email);
    }

    public int update(Service service) {
        return jdbcTemplate.update(ServiceSql.UPDATE_SERVICE,
                service.getTitle(),
                service.getDescription(),
                service.getCategory(),
                service.getPrice(),
                service.getProfileId());
    }

    public int deleteById(UUID id) {
        return jdbcTemplate.update(ServiceSql.DELETE_SERVICE, id);
    }
}