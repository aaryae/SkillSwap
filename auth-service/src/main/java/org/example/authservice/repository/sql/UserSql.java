package org.example.authservice.repository.sql;

public final class UserSql {

    private UserSql(){}

    public static final String INSERT_USER = """
        INSERT INTO users (email, password, username, bio, skills_offered, skill_wanted, role, profile_image, status, created_at, updated_at)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
        RETURNING id;
    """;

    
}

