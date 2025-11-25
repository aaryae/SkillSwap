package org.example.skillswap.repository;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.skillswap.repository.sql.RequestSql;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RequestRepository {

    public final JdbcTemplate jdbcTemplate;


    public String requestSkill(){
        return null;
    }

}
