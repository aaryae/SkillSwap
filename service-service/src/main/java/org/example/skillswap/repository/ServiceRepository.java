package org.example.skillswap.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.skillswap.model.Service;
import org.example.skillswap.repository.mapper.ServiceRowMapper;
import org.example.skillswap.repository.sql.ServiceSql;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ServiceRepository {

        private  final JdbcTemplate jdbcTemplate;


        public Optional<Service> getService(String email){

            try{
                return jdbcTemplate.query(ServiceSql.SELECT_SERVICE_BY_ID, new Object[]{email}, new ServiceRowMapper())
                        .stream()
                        .findFirst();            }
            catch (DataAccessException e){
                throw e;
            }

        }


}
