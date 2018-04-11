package com.bsuir.trainingcenter.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;


@TestConfiguration
public class TestConfig {

    @Bean
    public DataSource dataSource(){
        DataSource dataSource= DataSourceBuilder.create().username("root").password("root").url("jdbc:mysql://localhost:3306/training_service_db_test?useUnicode=true&useSSL=true").driverClassName("com.mysql.jdbc.Driver").build();
        return dataSource;
    }

}
