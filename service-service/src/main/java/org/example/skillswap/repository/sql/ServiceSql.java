package org.example.skillswap.repository.sql;

public final  class ServiceSql {


    private ServiceSql(){}

    public static final String SELECT_SERVICE="SELECT * from Service";
    public static final String SELECT_SERVICE_BY_ID="SELECT * from Service where id=?";
}
