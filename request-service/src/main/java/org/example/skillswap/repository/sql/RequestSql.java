package org.example.skillswap.repository.sql;

public final class RequestSql {


    private RequestSql(){}

    static final String CREATE_REQUEST="""
            INSERT INTO request (request_id, skill_id, requester_email, provider_email, message, status)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    static final String UPDATE_REQUEST_STATUS= """
          INSERT INTO request (status) VALUES (?)
          """;

    static final String FIND_REQUESTER_EMAIL= """
            SELECT FROM request where requester_id = ?
            """;

    static final String FIND_PROVIDER_EMAIL= """
            SELECT FROM request where provider_email = ?
            """;
}
