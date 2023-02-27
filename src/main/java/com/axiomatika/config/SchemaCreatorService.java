package com.axiomatika.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@RequiredArgsConstructor
public class SchemaCreatorService implements InitializingBean {

    private final DataSource dataSource;
    private final LiquibaseProperties liquibaseProperties;

    @Override
    public void afterPropertiesSet() {
        if (liquibaseProperties.isEnabled()) {
            JdbcTemplate template = new JdbcTemplate(dataSource);
            template.execute("CREATE SCHEMA IF NOT EXISTS " + liquibaseProperties.getDefaultSchema());
        }
    }
}
