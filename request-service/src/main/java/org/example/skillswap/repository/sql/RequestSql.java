package org.example.skillswap.repository.sql;


import org.springframework.stereotype.Component;

@Component
public final class RequestSql {


    private RequestSql(){}

    public static final String CREATE_REQUEST="""
            INSERT INTO request (request_id, skill_id, requester_email, provider_email, message, status)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    public static final String UPDATE_REQUEST_STATUS= """
          UPDATE requests SET status=?, updated_at=NOW() WHERE request_id=?
          """;

    public static final String FIND_REQUESTER_EMAIL= """
            SELECT FROM request where requester_id = ?
            """;

    public static final String FIND_PROVIDER_EMAIL= """
            SELECT FROM request where provider_email = ?
            """;
}
