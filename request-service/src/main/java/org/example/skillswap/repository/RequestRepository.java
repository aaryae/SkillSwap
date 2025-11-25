package org.example.skillswap.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.skillswap.dto.response.RequestResponse;
import org.example.skillswap.model.Request;
import org.example.skillswap.repository.mapper.RequestRowMapper;
import org.example.skillswap.repository.sql.RequestSql;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RequestRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RequestRowMapper rowMapper = new RequestRowMapper();

    public void save(Request request) {
        jdbcTemplate.update(
                RequestSql.CREATE_REQUEST,
                request.getRequestId(),
                request.getSkillId(),
                request.getRequesterEmail(),
                request.getProviderEmail(),
                request.getMessage(),
                request.getStatus().name()
        );

        log.info("[RequestRepository] Saved request id={}", request.getRequestId());
    }

    public int updateStatus(UUID requestId, String status) {
        int updated = jdbcTemplate.update(
                RequestSql.UPDATE_REQUEST_STATUS,
                status,
                requestId
        );

        if (updated == 0) {
            log.warn("[RequestRepository] No request found to update for id={}", requestId);
        }

        return updated;
    }

    public List<RequestResponse> findByRequester(String requesterEmail) {
        return jdbcTemplate.query(
                RequestSql.FIND_REQUESTER_EMAIL,
                rowMapper,
                requesterEmail
        );
    }

    public List<RequestResponse> findByProvider(String providerEmail) {
        return jdbcTemplate.query(
                RequestSql.FIND_PROVIDER_EMAIL,
                rowMapper,
                providerEmail
        );
    }
}
