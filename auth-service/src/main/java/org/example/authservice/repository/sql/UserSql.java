package org.example.authservice.repository.sql;

public final class UserSql {

    private UserSql(){}

    public static final String INSERT_USER = """
        INSERT INTO users (email, password, username, bio, skills_offered, skill_wanted, role, profile_image, status, created_at, updated_at)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
        RETURNING id;
    """;

    public static final String FIND_BY_ID =
            "SELECT * FROM users WHERE id = ?";

    public static final String FIND_BY_EMAIL =
            "SELECT * FROM users WHERE email = ?";

    public static final String UPDATE_USER = """
        UPDATE users
        SET username = ?, bio = ?, skills_offered = ?, skill_wanted = ?, profile_image = ?, updated_at = CURRENT_TIMESTAMP
        WHERE id = ?;
    """;

    public static final String DELETE_USER = """
        DELETE FROM users WHERE id = ?;
    """;
}




