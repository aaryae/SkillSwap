package org.example.authservice.repository;

import lombok.Builder;
import org.springframework.jdbc.core.JdbcTemplate;

@Builder
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    
}
