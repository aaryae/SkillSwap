package org.example.userservice.repository.sql;

public final class ProfileSql {

    private ProfileSql() {}

    public static final String  CREATE_PROFILE = """
            INSERT INTO profile (email, fullName,bio, skillsOffered, skillsWanted) VALUES (?,?,?,?,?,?)
            """;

    public static final String GET_USER_PROFILE_BY_ID = """
            SELECT * FROM profile where id = :id
            """;

    public static final String GET_USER_PROFILE_BY_EMAIL = """
            SELECT * FROM profile where email = :email
    """;

    public static final String  GET_USER_PROFILE="SELECT * FROM  profile";




}
