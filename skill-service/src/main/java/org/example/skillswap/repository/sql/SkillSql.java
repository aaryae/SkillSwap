package org.example.skillswap.repository.sql;

public class SkillSql {

    public static final String INSERT_SKILL = """
        INSERT INTO services (id, title, description, category, price, provider_email)
        VALUES (?, ?, ?, ?, ?, ?)
    """;

    public static final String SELECT_ALL = """
        SELECT * FROM services ORDER BY created_at DESC
    """;

    public static final String SELECT_BY_ID = """
        SELECT * FROM services WHERE id = ?
    """;

    public static final String SELECT_BY_PROVIDER_EMAIL = """
        SELECT * FROM services WHERE provider_email = ?
    """;

    public static final String UPDATE_SKILL = """
        UPDATE services 
        SET title = ?, description = ?, category = ?, price = ?, updated_at = NOW()
        WHERE id = ?
    """;

    public static final String DELETE_SKILL = """
        DELETE FROM services WHERE id = ?
    """;
}
